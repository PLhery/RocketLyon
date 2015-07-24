<?php
/**
 * Script which generate JSON station data from TCL's sqlite data from referential_android.sqlite - http://app.tcl.fr/flux/mobile_referential/android/3/referential_android.zip
 * Created by MrPointVirgule on 10/07/2015.
 */
$db = new SQLite3('referential_android.sqlite');


//1 - we get the stopzones (=stations, each card)
$results = $db->query('SELECT * from STOPZONE');
$stopzone=Array();
while ($row = $results->fetchArray()) {
	$stopzone[$row["_id"]]=Array(
	"id" => $row["stopzone_id"],
	"nom" => $row["stopzone_name"],
	"long" => $row["longitude"],
	"lat" => $row["latitude"]
	);
}

//2 - we get each stop
$results = $db->query("SELECT stop.stopzone as stopzoneID, stop.stop_id as stopID, stop.latitude, stop.longitude, line.display_name as ligne, line.line_id as lineID, terminus.stopzone_name as direction, line.color as couleur, line.mode FROM stop"
." JOIN stop_direction ON stop_direction.stop = stop._id"
." JOIN direction ON direction._id = stop_direction.direction"
." JOIN stopzone as terminus ON terminus._id = direction.stopzone"
." JOIN line ON line._id = direction.line"
." ORDER BY line.mode, ligne");
while ($row = $results->fetchArray()) {
	$stopzone[$row["stopzoneID"]]["lignes"][]=Array(
	"ligne" => $row["ligne"], //line (T1, bus42, MD)
	"direction" => $row["direction"], //Terminus
	"mode" => $row["mode"], //Is it a tramway, a metro, a bus etc.. see cardTemplate.php
	"stopID" => $row["stopID"], //Stop ID (to get the time before next train)
	"lineID" => $row["lineID"], //lineID (to get the time before next train) e.g T1A
	"long" => $row["longitude"], //To show the stop in google maps/street view
	"lat" => $row["latitude"],
	"couleur" => $row["couleur"] //Some lines - metros have a special color (in the logo MA MB.. and in the map)
	);
}

//And we generate a .json with it
header('Content-Type: application/json');
echo json_encode($stopzone);