    /**
    * o------------------------------------------------------------------------------o
    * | This package is licensed under the Phpguru license. A quick summary is       |
    * | that for commercial use, there is a small one-time licensing fee to pay. For |
    * | registered charities and educational institutes there is a reduced license   |
    * | fee available. You can read more  at:                                        |
    * |                                                                              |
    * |                  http://www.phpguru.org/static/license.html                  |
    * o------------------------------------------------------------------------------o
    *
    * © Copyright 2008,2009 Richard Heyes
    */
    
    /**
    * Create the ModalDialog object
    */
    ModalDialog = {};

    /**
    * Shows a modal dialog
    * 
    * @param string contentID ID of content layer to use HTML of
    * @param int    width     Width of dialog
    */
    function ModalDialog_Show(contentID, width)
    {
        /**
        * Hide all selects so they don't show through the ModalDialog layer
        */
        if (document.all) {
            var selectObjs = document.getElementsByTagName('select');
    
            for (var i=0; i<selectObjs.length; ++i) {
                if (!selectObjs[i].md_nohide) {
                    selectObjs[i].md_vis = selectObjs[i].style.visibility;
                    selectObjs[i].style.visibility = 'hidden';
                }
            }
        }
        
        /**
        * Create the background layer if necessary
        */
        var dialogBg = document.getElementById('modalBg');
        
        if (!dialogBg) {
            var bgDiv = document.createElement('div');
            bgDiv.id = 'modalBg';
            bgDiv.className = 'modalBg';
            bgDiv.style.opacity = 0.7;
        
            document.body.appendChild(bgDiv);
            
            var dialogBg = document.getElementById('modalBg');
            ModalDialog.background = dialogBg;
        }
        
        
        /**
        * Create the shadow layer
        */
        var dialogShadow = document.getElementById('modalShadow');
        
        if (!dialogShadow) {
            var shadowDiv = document.createElement('div');
            shadowDiv.id = 'modalShadow';
            shadowDiv.className = 'modalShadow';
            
            document.body.appendChild(shadowDiv);
            
            var dialogShadow = document.getElementById('modalShadow');
        }
    
        // Show the dialog
        var dialog = document.getElementById(contentID);
        dialog.style.visibility = 'hidden';
        dialog.style.width = width + 'px';
        
        dialogShadow.style.visibility = 'visible';
        dialogShadow.style.width = dialog.offsetWidth + 'px';
        dialogShadow.style.height = dialog.offsetHeight + 'px';
    
        /**
        * Insert the header into the dialog
        */
        var dialogHeader = document.createElement('div');
        dialogHeader.id = 'dialogHeader';
        dialogHeader.className = 'modalDialogHeader';
        dialog.appendChild(dialogHeader);
        dialogHeader = document.getElementById('dialogHeader');
    
        Fade(dialogBg, true, null, null, 70);
        Fade(dialog, true, null, null, 100);
        Fade(dialogShadow, true, null, null, 50, 'ModalDialog_FinishFade');
        
        
        // Moz stuff
        if (!document.all) {
            dialogBg.style.width  = document.body.scrollWidth + 'px';
            dialogBg.style.height = document.body.scrollHeight + 'px';

            dialog.style.left = document.body.offsetWidth / 2 - width / 2 + 'px';
            
            dialogHeader.style.width = (dialog.offsetWidth - 2) + 'px';
            dialogHeader.style.paddingTop = '5px';

            dialogShadow.style.left = (document.body.offsetWidth / 2 - width / 2) + 4 + 'px';
        
        // IE stuff
        } else {
            dialogHeader.style.width = dialog.offsetWidth + 'px';
            dialogBg.style.width = document.body.scrollWidth + 5;
        }
        
        // this is run for all browsers
        dialog.style.top = document.body.scrollTop + (document.body.clientHeight / 2) - (dialog.offsetHeight / 2);
        dialogShadow.style.top  = parseInt(dialog.style.top) + 4;
        
        /**
        * When the window is resized, reposition the dialog
        */
        window.onresize = ModalDialog.Reposition;
        window.onscroll = ModalDialog.Reposition;
        
        // Set visible dialog var
        ModalDialog.dialog = dialog;
        ModalDialog.shadow = dialogShadow;
    }
    
    
    /**
    * Closes a modal dialog
    */
    function ModalDialog_Close()
    {
        var dialogBg     = ModalDialog.background;
        var dialog       = ModalDialog.dialog;
        var dialogShadow = ModalDialog.shadow;
    
        
        // Lose the dialog header
        dialog.removeChild(document.getElementById('dialogHeader'));
    
        // Hide stuff
        dialogBg.style.MozOpacity = 0;
        dialogBg.style.visibility = 'hidden';
        
        dialog.style.MozOpacity = 0;
        dialog.style.visibility = 'hidden';
        dialogShadow.style.visibility = 'hidden';
        
        
        __visibleDialog = null;
        
        /**
        * Unhide all selects
        */
        var selectObjs = document.getElementsByTagName('select');
        
        for (var i=0; i<selectObjs.length; ++i) {
            if (!selectObjs[i].md_nohide) {
                selectObjs[i].style.visibility = selectObjs[i].md_vis;
            }
        }
    }
    
    function ModalDialog_FinishFade()
    {
        var shadow  = ModalDialog.shadow;
        var visible = ModalDialog.dialog;
    
        shadow.style.width = visible.offsetWidth;
        shadow.style.height = visible.offsetHeight;
        shadow.style.filter = 'Alpha(opacity=50) progid:DXImageTransform.Microsoft.Blur(pixelradius=2)';
    }

    /**
    * Repositions the dialod after a resize or scroll event
    */
    ModalDialog.Reposition = function ()
    {
        var dialog = ModalDialog.dialog;
        var shadow = ModalDialog.shadow;

        // Reposition in the vertical center
        dialog.style.top  = document.body.scrollTop + (document.body.clientHeight / 2) - (dialog.offsetHeight / 2);
        shadow.style.top = parseInt(dialog.style.top) + 4;

        // Reposition in the horizontal centre
        dialog.style.left  = document.body.scrollLeft + (document.body.clientWidth / 2) - (dialog.offsetLeft / 2);
        shadow.style.left = parseInt(dialog.style.left) + 4;
    }
    
    /**
    * Assign the objects functions
    */
    ModalDialog.Show = ModalDialog_Show;
    ModalDialog.Close = ModalDialog_Close;