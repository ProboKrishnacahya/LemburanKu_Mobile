<?php 

$host = 'localhost';
$user = 'root';
$pass = '';
$dbname = 'lemburanku';

$conn = new mysqli($host, $user, $pass, $dbname);
$now = new DateTime("now", new DateTimeZone('Asia/Jakarta'));
$time = $now->format('Y-m-d H:i:s');

if(!$conn){
    die("Connection failed: ".$conn->connect_error);
}

?>