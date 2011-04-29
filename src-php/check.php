<?php

require_once 'setting.php';
require_once 'connect.php';
$user = $_GET["pn"];
$passw = $_GET["sh"];
$salt = $_GET["si"];
$dbquery = "SELECT " . "*" ." FROM " . $user_table. " WHERE " . $user_name_field . "=" ."'" .$user."'" ;

$thedata = mysql_query($dbquery) or die(mysql_error());
$numofusers = mysql_num_rows($thedata);
$row = mysql_fetch_assoc($thedata);

if(md5($row[$user_password_field] . $mainsalt . $salt)==$passw){
echo "yes\n";
}
else {
echo "no\n";
}
?>