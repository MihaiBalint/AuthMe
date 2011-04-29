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
if($row[$user_password_field]==md5($_GET["pass"])){
	echo "correct";
}
else {
	echo "Incorrect";
}
?>