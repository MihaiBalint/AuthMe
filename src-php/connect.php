<?php 

require_once 'setting.php';

mysql_connect($hostname,$username,$password)or die(mysql_error());
mysql_select_db($database) or die(mysql_error());

?>