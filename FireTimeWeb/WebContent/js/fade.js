///////////////////////////////////////////////////////////////////////
//     This fade library was designed by Erik Arvidsson for WebFX    //
//                                                                   //
//     For more info and examples see: http://webfx.eae.net          //
//     or contact Erik at http://webfx.eae.net/contact.html#erik     //
//                                                                   //
//     Feel free to use this code as lomg as this disclaimer is      //
//     intact.                                                       //
///////////////////////////////////////////////////////////////////////


var __fadeArray = new Array();    // Needed to keep track of wich elements are animating

//////////////////  fade  ////////////////////////////////////////////////////////////
//                                                                                  //
//   parameter: fadeIn                                                              //
// description: A boolean value. If true the element fades in, otherwise fades out  //
//              The steps and msec are optional. If not provided the default        //
//              values are used. Opacity is optional and specifies the start/end    //
//              opacity values. onfinish is used when the fadein/out has finished   // 
//              and is called (so pass in a function name)                          //
//////////////////////////////////////////////////////////////////////////////////////

function Fade(el, fadeIn, steps, msec, opacity, onfinish)
{
    if (steps    == null)  steps   = 4;
    if (msec     == null)  msec    = 25;
    if (opacity  == null)  opacity = 100;
    if (onfinish == null)  onfinish = '';

    if (el.fadeIndex == null) {
        el.fadeIndex = __fadeArray.length;
    }

    __fadeArray[el.fadeIndex] = el;

    if (el.style.visibility == "hidden") {
        el.style.display  = 'block';
        el.fadeStepNumber = 0;
    } else {
        el.fadeStepNumber = steps;
    }

    if (fadeIn) {
        el.style.filter = "Alpha(Opacity=0)";
        el.style.MozOpacity = '0';
    } else {
        el.style.filter = "Alpha(Opacity=" + opacity + ")";
        el.style.MozOpacity = opacity / 100;
    }

    window.setTimeout("RepeatFade(" + fadeIn + "," + el.fadeIndex + "," + steps + "," + msec + ", " + opacity + ", '" + onfinish + "')", msec);
}

//////////////////////////////////////////////////////////////////////////////////////
//  Used to iterate the fading                                                      //
//////////////////////////////////////////////////////////////////////////////////////
function RepeatFade(fadeIn, index, steps, msec, opacity, onfinish)
{
    el = __fadeArray[index];

    c = el.fadeStepNumber;
    if (el.fadeTimer != null) {
        window.clearTimeout(el.fadeTimer);
    }

    if (c == 0 && !fadeIn) {            // Done fading out!
        el.style.visibility = "hidden"; // If the platform doesn't support filter it will hide anyway
        el.style.display    = "none"; // If the platform doesn't support filter it will hide anyway

        if (onfinish) {
            eval(onfinish + "()");
        }
        return;

    } else if (c == steps && fadeIn) {    // Done fading in!
        el.style.filter = "Alpha(Opacity=" + opacity + ")";
        el.style.MozOpacity = opacity / 100;
        el.style.visibility = "visible";

        if (onfinish) {
            eval(onfinish + "()");
        }
        return;

    } else {
        fadeIn ? c++ : c--;
        el.style.visibility = "visible";
        el.style.filter = "Alpha(Opacity=" + opacity*c/steps + ")";
        el.style.MozOpacity = (opacity / 100) * c/steps;

        el.fadeStepNumber = c;
        el.fadeTimer = window.setTimeout("RepeatFade(" + fadeIn + "," + index + "," + steps + "," + msec + ", " + opacity + ", '" + onfinish + "' )", msec);
    }
}