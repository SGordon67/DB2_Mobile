<?php
// setup the database for query, grab variables from post
$mysqli = new mysqli('localhost', 'root', '', 'DB2');
$email = $_POST['email'];
$password = $_POST['password'];
$emailIn = $_POST['emailIn'];
$passwordIn = $_POST['passwordIn'];
$newEmail = $_POST['newEmail'];
$user = $_POST['user'];


if ($email != $emailIn){
    $response["result"] = "failed";
    $response["success"] = "false";
    echo json_encode($response);
} else if($password != $passwordIn){
    $response["result"] = "failed";
    $response["success"] = "false";
    echo json_encode($response);
} else{
    // get the users ID
    $qGetId = "SELECT id FROM users WHERE email = '$email'";
    $id = $mysqli->query($qGetId);
    $targetID = mysqli_fetch_array($id);

    // get all their information
    $qGetInfo = "SELECT * FROM users WHERE email = '$email'";
    $result = $mysqli->query($qGetInfo);
    $testrow = mysqli_fetch_array($result);

    if(empty($targetID)){
        $response["result"] = "failed";
        $response["success"] = "false";
        echo json_encode($response);
    } else if($password != $testrow['password']){
        $response["result"] = "failed";
        $response["success"] = "false";
        echo json_encode($response);
    }else{
        // validate new email
        $email = trim(htmlspecialchars($_POST['email']));
        $email = filter_var($email, FILTER_VALIDATE_EMAIL);
        if ($email === false) {
            $response["result"] = "failed";
            $response["success"] = "false";
            echo json_encode($response);
        } else{
            // query for updating the email based on input
            $updateEmail = "UPDATE users 
                            SET email='$newEmail' 
                            WHERE email = '$email'";
            $resEmail = $mysqli->query($updateEmail);
            $response["result"] = "Successful";
            $response["success"] = "true";
            echo json_encode($response);
        }
    }
}
?>