<?php
// setup the database for query, grab variables from post
$mysqli = new mysqli('localhost', 'root', '', 'DB2');
$email = $_POST['email'];
$password = $_POST['password'];
$newPassword = $_POST['newPassword'];
$user = $_POST['user'];

// query for updating the email based on input
$updatePass = " UPDATE users 
                SET password ='$newPassword' 
                WHERE email = '$email'";
$resPass = $mysqli->query($updatePass);
$response["result"] = "Successful";
$response["success"] = "true";
echo json_encode($response);
?>