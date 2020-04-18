<?php
$user = "admin";
$bool = false;
$mysqli = new mysqli('localhost', 'root', '', 'DB2');
$email = $_POST['email'];
$password = $_POST['password'];

// get the target ID entered
$qGetId = "SELECT id FROM users WHERE email = '$email'";
$id = $mysqli->query($qGetId);
$targetID = mysqli_fetch_array($id);

// get an array of all admin ID's
$aids = [];
$qadminIDs = "SELECT admin_id from admins";
$res = $mysqli->query($qadminIDs);
while($row = mysqli_fetch_assoc($res)){
    foreach($row as $cname => $cvalue){
        array_push($aids,$cvalue);
    }
}

// grab admin info from user table 
$qGetInfo = "SELECT * FROM users WHERE email = '$email'";
$result = $mysqli->query($qGetInfo);
$testrow = mysqli_fetch_array($result);

// check if target ID is in array of admin ID's
if(empty($targetID)){
    $response["success"] = "false";
    echo json_encode($response);
} else if (!in_array($targetID['id'], $aids)){
    $response["success"] = "false";
    echo json_encode($response);
} else if($password != $testrow['password']){
    $response["success"] = "false";
    echo json_encode($response);
}else{
    // get all of the information from database starting with data in the users table
    // then accessing admin email using admin id

    $response["name"] = $testrow['name'];
    $response["email"] = $testrow['email'];
    $response["phone"] = $testrow['phone'];
    $response["password"] = $testrow['password'];

    $response["success"] = "true";
    echo json_encode($response);
}
?>