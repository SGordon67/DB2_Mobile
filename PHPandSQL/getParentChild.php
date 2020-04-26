<?php
$mysqli = new mysqli('localhost', 'root', '', 'DB2');
$email = $_POST['email'];

// get the target ID entered
$qGetId = "SELECT id FROM users WHERE email = '$email'";
$id = $mysqli->query($qGetId);
$targetID = mysqli_fetch_array($id);

// get the child info that correspond to the student id's
$sEmails = [];
$getSEmails = "SELECT * FROM users WHERE id IN 
                (SELECT student_id AS id FROM students WHERE parent_id = {$targetID['id']})";
$sInfoRes = $mysqli->query($getSEmails);

$response = array();
$i = 0;
while($row = mysqli_fetch_array($sInfoRes)){
    $response[strval($i) . "sid"] = $row['id'];
    $response[strval($i) . "sName"] = $row['name'];
    $response[strval($i) . "sEmail"] = $row['email'];
    $i = $i + 1;
}

echo json_encode($response);
?>