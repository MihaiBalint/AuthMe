<?php

require_once 'setting.php';
require_once 'connect.php';

$dbqueryphrase = "SELECT " . $user_name_field ." FROM " . $user_table;
$dbinfo = mysql_query($dbqueryphrase);
$count = mysql_num_rows($dbinfo);

echo $count."<br/>";

?>