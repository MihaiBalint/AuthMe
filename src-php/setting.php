<?php 
//Hash Method
//Mysql Database connection info.

$hostname="localhost";
$username="root";
$password="";
$database="minecraft";

//database fields to use
//here make a random text to use to encrypt the password for transfer
$mainsalt = "GMCPORPisTheBest";
//here identify the table from the database to use
$user_table = "forum_users";
//here identify the user name feild
$user_name_field = "username_clean";
//here identify the user pass field in md5
$user_password_field = "password_md5";


//Whitelist Settings
/*
This whitelist goes by group feilds, you can set which group on your
forum or website you want able to connect to your server. 
Hint: You can set this to any type of felid and allow any resault you like
*/
//Using the whitelist? True for yes, false for no.
$using_whitelist=false;
//here identify the feild to use as group id,
$group_id_feild = "";
//List the allowed groups.
$groups_allowed = 0;
?>
