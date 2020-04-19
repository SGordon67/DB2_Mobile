<?php
// setup the database for query, grab variables from post
$mysqli = new mysqli('localhost', 'root', '', 'DB2');
$email = $_POST['email'];
$password = $_POST['password'];
$emailIn = $_POST['emailIn'];
$passwordIn = $_POST['passwordIn'];
$newPhone = $_POST['newPhone'];
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
        // validate phone number
        if(!preg_match('/^[0-9]{10}+$/', $newPhone)) {
            $response["result"] = "failed";
            $response["success"] = "false";
        } else{
            // query for updating the email based on input
            $updatePhone = "UPDATE users 
                            SET phone='$newPhone' 
                            WHERE email = '$email'";
            $resPhone = $mysqli->query($updatePhone);
            $response["result"] = "Successful";
            $response["success"] = "true";
            echo json_encode($response);
        }
    }
}
?>