/**
 * Script which manage cards actions (buttons - selection of stops - time before next train etc)
 * Created by MrPointVirgule on 09/07/2015.
 */
var pano;
var markers=[];
var selectedMarker;
var infowindow;

/**
 * When we dynamically added some cards, we have to call that function to refresh cards actions
 */
function refreshCardsEvents()
{
    pano=null; //Contains street view panorama
    markers=[]; //Contains markers at each stop
    selectedMarker=null; //what marker is now selected
    infowindow=null; //Contains the opened "infowindow" (popup on the map)

    refreshTime();//we refresh the "next train" time left

    $(".indicationsTransport").click(function() { //clicking on a ".indicationtransport" (stop infos) trigger the select function
        var stopCard = $(this).parent().parent();
        select(stopCard,$(this).attr("transpID"));
    });

    $("a.streetview").click(function () { //clicking on the street view button open the panorama

        var stopCard = $(this).parent().parent();
        var mapCanvas = stopCard.children().children(".moreInfo");
        var location = new google.maps.LatLng(stopCard.attr("latitude"), stopCard.attr("longitude"));

        if(mapCanvas.height()==0) { //if the panorama is now closed, we open it
            stopCard.addClass("select"); //All .indicationsTransport are selectable

            if(pano) { //If there is already an opened panorama, we close it
                $("a.streetview:contains(cacher)").click();
                pano=null;
                markers=[];
            }

            $(this).text("cacher"); //text "Hide"

            pano = new google.maps.StreetViewPanorama( //We create the panorama, we draw it on mapCanvas - see https://developers.google.com/maps/documentation/javascript/streetview
                mapCanvas[0], {
                    position: location,
                    disableDefaultUI: true,
                    enableCloseButton: false,
                    zoomControl: true,
                    linksControl: true,
                    pov: {
                        heading: 0,
                        pitch: 0,
                        zoom: 0
                    }
                });

            google.maps.event.addListener(pano, 'position_changed', function() { //if the user navigate in another panorama (change his street etc..)
                if(selectedMarker!=undefined) {
                    markers[selectedMarker].setAnimation(google.maps.Animation.BOUNCE); //We redisplay the "bounce" animation on the selected marker
                }
            });

            //We add those markers
            stopCard.children().children(".indicationsTransport").each(function() { //One by stop
                var id=$(this).attr("transpID");

                markers[id] = new google.maps.Marker({
                    position: new google.maps.LatLng($(this).attr("latitude"), $(this).attr("longitude")),
                    map: pano,
                    animation: google.maps.Animation.DROP,
                    icon: 'https://mt.google.com/vt/icon/name=icons/spotlight/transit/rail_large.png&scale=1', //tramway icon
                    title: $(this).attr("titre")
                });

                google.maps.event.addListener(markers[id] , 'click', function() { //When we click a marker, we select the stop.
                    select(stopCard, id)
                });
            });

            mapCanvas.css("max-height", (($(window).width() > $(window).height()) ? $(window).height() : $(window).width()) / 1.5 + "px") //The max height of the street view panorama is based on the screen size we are using (mobile etc)
                .height("400px");
            pano.setVisible(true);

        } else { //if the panorama is already opened

            pano.setVisible(false); //We close it
            $(this).text("Street view");
            mapCanvas.height(0);
            stopCard.removeClass("select");
        }

    });


    $(".mdl-tooltip").each(function() { //We refresh each tooltip element of the card (we have to with getmdl.io)
        componentHandler.upgradeElement($(this)[0], 'MaterialTooltip')
    });
}


/**
 * Select a stop
 * @param stopCard the card (of a station) where the stop is
 * @param id the id of that stop
 */
function select(stopCard, id) {
    var indication = stopCard.children().children(".indicationsTransport[transpID="+id+"]"); //The .indicationsTransport corresponding to the stop we selected
    if(stopCard.hasClass("select")) { //If we are in select mode (pano opened..)

        stopCard.children().children(".indicationsTransport.selected").removeClass("selected"); //We change the line which is selected
        indication.addClass("selected");

        if(infowindow) //And we close the opened infowindow
            infowindow.close();

        if(Math.pow(pano.getPosition().lat()*10000-indication.attr("latitude")*10000,2)+Math.pow(pano.getPosition().lng()*10000-indication.attr("longitude")*10000,2)>30) //if we are 30 meters from the stop
            pano.setPosition(new google.maps.LatLng(indication.attr("latitude"), indication.attr("longitude"))); //We get closer

        markers[id].setAnimation(google.maps.Animation.BOUNCE); //We add the animation to the marker
        if(selectedMarker!=undefined)
            markers[selectedMarker].setAnimation(null); //(stop the animation on the old marker
        selectedMarker=id;

        infowindow = new google.maps.InfoWindow({ //Creation of the infowindow (the popup on the map/street view)
            content: "<div class='infowindow'>"+indication.html()+"</div>"
        });
        infowindow.open(pano,markers[id]);

        google.maps.event.addListener(infowindow, 'closeclick', function() { //If we close the infowindow, we unselect the stop
            markers[selectedMarker].setAnimation(null);
            stopCard.children().children(".indicationsTransport.selected").removeClass("selected");
            selectedMarker=undefined;
        });
    }
}

/**
 * Load the "time before next train" (in .numberCircle)
 */
function refreshTime() {
    var stops = []; //List of all the stops IDs
    $(".indicationsTransport").each(function()  {
        stops.push($(this).attr("stopID"));
    });

    $.getJSON("data/TCLrefresh.php?q="+stops.join(), function( data ) { //We should push this list to http://app.tcl.fr/mob_app/appli_mobile/gethorairespoteau/android/REFRESH/ in phonegap.. But in web, we need a "proxy"
        $(this).children().children(".attente2,.attente1").removeClass("heure"); //If there is a class "heure" (that mean a smaller font for formats like 6h30)

        $(".indicationsTransport").each(function()  { //each line

            if(data.DATA[$(this).attr("stopID")] && data.DATA[$(this).attr("stopID")][$(this).attr("lineID")]) { //If we have the data we wanted from the API
                var pas1 = data.DATA[$(this).attr("stopID")][$(this).attr("lineID")][0].passage1; //The data returned for next train
                if(pas1=="Proche") //If it's close
                    $(this).children().children(".attente1").html('<i class="material-icons">directions_bus</i>'); //We put a tram icon
                else if(pas1.indexOf("h") > 0) //If it's in more than one hour (so the return format is e.g "20h45")
                {
                    $(this).children().children(".attente1").text(pas1);
                    $(this).children().children(".attente1").addClass("heure"); //The font is smaller, thx to the class "heure"
                } else {
                    pas1 = pas1.replace(" min",""); //If we have something like "3 min", we just write a 3
                    $(this).children().children(".attente1").text(pas1);
                }

                var pas2 = data.DATA[$(this).attr("stopID")][$(this).attr("lineID")][0].passage2; //Same for the second train
                if(pas2) {
                    if (pas2 == "Proche") //If it's close
                        $(this).children().children(".attente2").text("P");
                    else if (pas2.indexOf("h") > 0) //If it's in more than one hour
                    {
                        $(this).children().children(".attente2").text(pas2);
                        $(this).children().children(".attente2").addClass("heure");
                    } else {
                        pas2 = pas2.replace(" min", "");
                        $(this).children().children(".attente2").text(pas2);
                    }
                }
            }

        });
    });
}