<?php
$mysqli = new mysqli('localhost', 'root', '', 'DB2');
$email = $_POST['email'];
$sMid = $_POST['mid'];
$mid = intval($sMid);

// get the id of the user
$getuserid = "SELECT id FROM users WHERE email = '$email'";
$idResult = $mysqli->query($getuserid);
$idArr = mysqli_fetch_array($idResult);
$id = $idArr['id'];

// remove them from the desired meeting
$removeFromMeeting = "DELETE FROM enroll WHERE mentee_id = $id AND meet_id = $mid";
$removeResult = $mysqli->query($removeFromMeeting);

// if the user is not a mentee for any other meeting, remove them from mentee table
$isMentee2 = "SELECT * FROM enroll WHERE mentee_id = $id";
$isMenteeRes2 = $mysqli->query($isMentee2);
$noRows3 = mysqli_num_rows($isMenteeRes2);
if($noRows3 == 0){
    $removeMentee = "DELETE FROM `mentees` WHERE mentee_id = $id";
    $RemoveMenteeRes = $mysqli->query($removeMentee);
}
$response["success"] = "true";
echo json_encode($response);
?>