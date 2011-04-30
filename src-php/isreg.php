<?php

require_once 'setting.php';
require_once 'connect.php';
$user = strtolower($_GET["pn"]);

switch($using_whitelist){
	case true:
		$dbquerya = "SELECT " . $user_name_field ." FROM " . $user_table. " WHERE LOWER(" . $user_name_field . ")=" ." LOWER('" .$user."')" . " AND ". $group_id_feild . "=" . $groups_allowed;
	break;
	case false:
		$dbquerya = "SELECT " . $user_name_field ." FROM " . $user_table. " WHERE LOWER(" . $user_name_field . ")=" ."LOWER('" .$user."')";
	break;
}

$thedata = mysql_query($dbquerya) or die(mysql_error());
$querydata = mysql_num_rows($thedata);

if ($querydata>0){
	echo "YES\n";
}

else{
	echo "NO\n";
}

?>
