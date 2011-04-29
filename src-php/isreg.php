<?php

require_once 'setting.php';
require_once 'connect.php';
$user = $_GET["pn"];
$dbquery = "SELECT " . $user_name_field ." FROM " . $user_table. " WHERE " . $user_name_field . "=" ."'" .$user."'" ;

$thedata = mysql_query($dbquery) or die("<html>no<br/></html>");
$numofusers = mysql_num_rows($thedata);

if ($numofusers>0){
	echo "yes\n<br/>";
}
else{
	echo "no\n<br/>";
}

?>