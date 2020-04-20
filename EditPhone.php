<?php
// setup the database for query, grab variables from post
$mysqli = new mysqli('localhost', 'root', '', 'DB2');
$email = $_POST['email'];
$password = $_POST['password'];
$newPhone = $_POST['newPhone'];
$user = $_POST['user'];

// validate phone number
if(!preg_match('/^[0-9]{10}+$/', $newPhone)) {
    $response["success"] = "false";
    echo json_encode($response);
} else{
    // query for updating the email based on input
    $updatePhone = "UPDATE users 
                    SET phone='$newPhone' 
                    WHERE email = '$email'";
    $resPhone = $mysqli->query($updatePhone);
    $response["success"] = "true";
    echo json_encode($response);
}
?>