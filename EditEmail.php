<?php
// setup the database for query, grab variables from post
$mysqli = new mysqli('localhost', 'root', '', 'DB2');
$email = $_POST['email'];
$newEmail = $_POST['newEmail'];
$user = $_POST['user'];

// validate new email
$emailIn = trim(htmlspecialchars($_POST['newEmail']));
$emailIn = filter_var($emailIn, FILTER_VALIDATE_EMAIL);
if ($emailIn === false) {
    $response["success"] = "false";
    echo json_encode($response);
} else{
    // query for updating the email based on input
    $updateEmail = "UPDATE users 
                    SET email='$newEmail' 
                    WHERE email = '$email'";
    $resEmail = $mysqli->query($updateEmail);
    $response["success"] = "true";
    echo json_encode($response);
}
?>