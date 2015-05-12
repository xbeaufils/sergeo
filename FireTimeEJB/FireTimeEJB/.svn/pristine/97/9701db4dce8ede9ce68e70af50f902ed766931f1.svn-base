/**
 * 
 */
package fr.firesoft.fireTime.impl;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.security.auth.x500.X500Principal;

import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.jce.PrincipalUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.openssl.PasswordFinder;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import org.bouncycastle.x509.extension.AuthorityKeyIdentifierStructure;
import org.bouncycastle.x509.extension.SubjectKeyIdentifierStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

import fr.firesoft.fireTime.bean.AdminLocal;
import fr.firesoft.fireTime.bean.EffectifManagerLocal;
import fr.firesoft.fireTime.entity.EchelonOrganigramme;
import fr.firesoft.fireTime.entity.Organisation;
import fr.firesoft.fireTime.entity.customer.Client;
import fr.firesoft.fireTime.entity.horaire.StatutPeriode;

/**
 * @author xbeaufils
 *
 */
@Stateless
@Local (AdminLocal.class)
public class Admin implements AdminLocal {
	   @PersistenceContext(unitName = "FireTime")
	    private EntityManager em;

	   @Resource(mappedName="mailSession_firesoft")
	    private Session mailSession;

	   @Resource (name="certificate_ca_path")
       	private String certificateCAPath;
	   @Resource (name="certificate_ca_key")
	   	private String certificateCAKey;
	   @Resource (name="certificats_path")
       	private String certificatesPath ;	    
	   
	   @EJB
	   		EffectifManagerLocal effectifManager;
	   
	   
	   private static Logger log = LoggerFactory.getLogger(Admin.class.getName());
	/**
	 * 
	 */
	public Admin() {
	}

	@Override
	public void saveStatut(StatutPeriode aStatut) {
		if (aStatut.getIdfStatut() != null)
			em.merge(aStatut);
		else
			em.persist(aStatut);
		
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.AdminLocal#createClient(java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void createClient(String email, String centre,
			String organisationName, String adresse, 
			String adresseComplementaire, String codePostal, String ville) {
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		StatusPrinter.print(lc);

		try {
			Calendar today = Calendar.getInstance();
			Calendar expiredCal = Calendar.getInstance();
			expiredCal.add(Calendar.MONTH, 1);
			Organisation orga = em.find(Organisation.class, organisationName);
			if (orga == null) {
				orga = new Organisation();
				orga.setNom(organisationName);
				em.persist(orga);
			}
			EchelonOrganigramme echelon = null;
			Query query = em.createNamedQuery("EchelonOrganigramme.selectByName");
			query.setParameter("aName", centre);
			List<EchelonOrganigramme> lstEchelon = query.getResultList();
			if (lstEchelon.size() == 0 ) {
				// Creation de l'echelon de l'organigramme
				echelon = new EchelonOrganigramme();
				echelon.setLibelle(centre);
				echelon.setOrganisation(orga);
				em.persist(echelon);
			}
			else
				echelon = lstEchelon.get(0);
			Client client = new Client();
			client.setLibelle(centre);
			client.setAdresse(adresse);
			client.setAdresseComplementaire(adresseComplementaire);
			client.setCodePostal(codePostal);
			client.setVille(ville);
			client.setEmail(email);
			client.setDateValidite(expiredCal.getTime());
			client.setEchelon(echelon);
			em.persist(client);
			// ---- Creation du certificat
			this.createCertificate(echelon,today.getTime(), expiredCal.getTime());
			// ------- Envoi du message
			Message mesg = new MimeMessage(mailSession);
			mesg.setFrom(new InternetAddress("contact@firesoft.fr"));
			InternetAddress toAddress = new InternetAddress(email);
			mesg.addRecipient(Message.RecipientType.TO, toAddress);
			mesg.setSubject("Certificat pour Sergeo");
		    // create and fill the first message part
		    MimeBodyPart mbp1 = new MimeBodyPart();
		    mbp1.setText("En pièce jointe, votre certificat à importer dans votre navigateur web.");

		    // create the second message part
		    MimeBodyPart mbp2 = new MimeBodyPart();

		    // attach the file to the message
		    FileDataSource fds = new FileDataSource(certificatesPath + "/" + centre + ".p12");
		    //mbp2.setDataHandler(new DataHandler(rawData));
		    mbp2.setDataHandler(new DataHandler(fds));
		    mbp2.setFileName(fds.getName());
		    // create the Multipart and add its parts to it
		    Multipart mp = new MimeMultipart();
		    mp.addBodyPart(mbp1);
		    mp.addBodyPart(mbp2);
		    // add the Multipart to the message
		    mesg.setContent(mp);
	        Transport transport = null;
            transport = mailSession.getTransport(new InternetAddress(email));
            transport.connect();
            transport.sendMessage(mesg, new Address[] {new InternetAddress(email)});
            transport.close();
		}
		catch(MessagingException e) {
			e.printStackTrace();
		}
 	}

	@SuppressWarnings("unchecked")
	private void createCertificate(EchelonOrganigramme echelon, Date beginDate, Date expiredDate  ) {
		Security.addProvider( new BouncyCastleProvider());
		BouncyCastleProvider provider = new BouncyCastleProvider();
		try {
			// Read CAcertificate
			InputStream inputCA = new FileInputStream(certificateCAPath);
			CertificateFactory factory = CertificateFactory.getInstance("X509",provider);
			Certificate caCert =  factory.generateCertificate(inputCA);
			log.debug("Certificate {}", ((X509Certificate) caCert).getSubjectX500Principal().toString());
			log.debug("Class certificate {}", caCert.getClass().getName());
			log.debug("Centre {}", echelon.getLibelle());
			inputCA.close();
			PEMReader reader = new PEMReader(new FileReader(certificateCAKey),
			        new PasswordFinder() {
						
			    public char[] getPassword() {
			        return "passphase".toCharArray();
			    }
			});
			KeyPair keyCA = (KeyPair) reader.readObject();
			// Creation du certificat client
			X509V3CertificateGenerator generator = new X509V3CertificateGenerator();
			Vector<String> vectO = PrincipalUtil.getSubjectX509Principal( (X509Certificate) caCert).getValues(X509Name.O);
			Vector<String> vectOU = PrincipalUtil.getSubjectX509Principal( (X509Certificate) caCert).getValues(X509Name.OU);
			Vector<String> vectL = PrincipalUtil.getSubjectX509Principal( (X509Certificate) caCert).getValues(X509Name.L);
			Vector<String> vectST = PrincipalUtil.getSubjectX509Principal( (X509Certificate) caCert).getValues(X509Name.ST);
			Vector<String> vectC = PrincipalUtil.getSubjectX509Principal( (X509Certificate) caCert).getValues(X509Name.C);
			X500Principal subjectName = new X500Principal("CN=" + echelon.getIdfEchelon() +", " +
					"OU=" + vectOU.get(0) + ", " +
					"O="  + vectO.get(0)  + ", " +
					"L="  + vectL.get(0)  + ", " + 
					"ST=" + vectST.get(0) + ", " +
					"C="  + vectC.get(0));
		    KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA", provider);
		    kpGen.initialize(1024, new SecureRandom());
	
			KeyPair keyPair = kpGen.generateKeyPair();
	
			generator.setSerialNumber(BigInteger.valueOf(beginDate.getTime()));
			generator.setIssuerDN( ((X509Certificate)caCert).getSubjectX500Principal());
			generator.setNotBefore(beginDate);
			generator.setNotAfter(expiredDate);
			generator.setSubjectDN(subjectName);
			generator.setPublicKey(keyPair.getPublic());
			generator.setSignatureAlgorithm("SHA256WithRSAEncryption");
	
			generator.addExtension(X509Extensions.AuthorityKeyIdentifier, false,
			                        new AuthorityKeyIdentifierStructure((X509Certificate) caCert));
			generator.addExtension(X509Extensions.SubjectKeyIdentifier, false,
			                        new SubjectKeyIdentifierStructure(keyPair.getPublic()));
/*			generator.addExtension(X509Extensions.SubjectAlternativeName, false, 
					new DEROctetString(new String ("1").getBytes()));
*/			X509Certificate cert = generator.generate( keyCA.getPrivate(), "BC");   // note: private key of CA
			// saving certificat
			KeyStore store = KeyStore.getInstance("PKCS12", "BC");
			store.load(null, null);
			store.setKeyEntry(echelon.getLibelle(), keyPair.getPrivate(), "firesoft".toCharArray(), new Certificate[]{cert, caCert});
			FileOutputStream fOut = new FileOutputStream(certificatesPath + "/" + echelon.getLibelle() + ".p12");
			store.store(fOut, "firesoft".toCharArray());
		}
		catch (IOException e) {
			System.err.println("Erreur IO");
			e.printStackTrace();
		}
		catch (CertificateParsingException e) {
			e.printStackTrace();
		}
		catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		catch(SignatureException e) {
			e.printStackTrace();
		}
		catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		catch(CertificateException e) {
			e.printStackTrace();
		}
		catch( KeyStoreException e) {
			e.printStackTrace();
		}
		
	}

}
