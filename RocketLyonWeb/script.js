/**
 * Script which manage page behavior
 * Created by MrPointVirgule on 09/07/2015.
 */
loadModel(function() { //When the data stations.json is loaded

    $("#search").on('input', function() { //If we change the search request, we refresh the request
        location.hash=$(this).val(); //We refresh the hash of the website
        $("#body").html("");
        var stops = searchStop($(this).val()); //We select all stops containing the search value
        jQuery.each(stops, function( key, arret ) {
            $("#body").append(stopToCard(arret)); //We generate the cards and add them to the site
        });
        refreshCardsEvents(); //We refresh the cards events (what a surprise !)
        if(stops.length>=10) //If we have 10+ cards (the max displayed - for performance reasons - the 10 closers to the user)
            $("#body").append("<div class='mdl-card mdl-shadow--2dp mdl-cell mdl-cell--12-col mdl-grid' style='min-height: 0;'><div class='mdl-card__title'><h2 class='mdl-card__title-text'>Soyez plus précis dans les termes de recherche...</h2></div></div>"); //We warn the user that maybe other stops exist with these terms
    });

    if(location.hash) { //If we have a search term in the url
        $("#search").val(location.hash.substring(1)); //We change the search input
        $("#searchgroup").addClass("is-focused"); //And we expand the search zone
    }

    $("#search").trigger("input"); //When the data is loaded we refresh the cards, ether with the search value if there is an hash, or with no value (so it display the closests stops)

    window.setInterval(refreshTime, 15000); //Refresh time before next train each 15 seconds
});


var long;
var lat;
navigator.geolocation.getCurrentPosition( //Write the location of the user in long and lat if the user agreed.
		function(position) {
			lat = position.coords.latitude;
			long = position.coords.longitude;
			$("#search").trigger("input");
		},
		function(error){
			console.log(error.message);
		}
);

