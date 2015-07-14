<?php
/**
 * Proxy to get time before next train -  if we are on the web version (else we have to add permisions for app.tcl.fr)
 */
$search = $str = preg_replace( //format like 3260,3261,3512  (stop IDs)
  array(
    '/[^\d,]/',    // Matches anything that's not a comma or number.
    '/(?<=,),+/',  // Matches consecutive commas.
    '/^,+/',       // Matches leading commas.
    '/,+$/'        // Matches trailing commas.
  ),
  '',              // Remove all matched substrings.
  $_GET['q']
);
header('Access-Control-Allow-Origin: *');
echo file_get_contents("http://app.tcl.fr/mob_app/appli_mobile/gethorairespoteau/android/REFRESH/".$search);