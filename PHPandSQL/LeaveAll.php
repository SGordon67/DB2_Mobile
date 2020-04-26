<?php
$mysqli = new mysqli('localhost', 'root', '', 'DB2');
$email = $_POST['email'];

// get the id of the user
$getuserid = "SELECT id FROM users WHERE email = '$email'";
$idResult = $mysqli->query($getuserid);
$idArr = mysqli_fetch_array($idResult);
$id = $idArr['id'];

// remove from all mentee meetings
$leaveMenteeMeeting = "DELETE FROM enroll WHERE mentee_id = '$id'";
$leaveRes = $mysqli->query($leaveMenteeMeeting);

// remove from mentee table
$leaveMentee = "DELETE FROM mentees WHERE mentee_id = '$id'";
$leaveRes2 = $mysqli->query($leaveMentee);

// remove from all mentor meetings
$leaveMentorMeeting = "DELETE FROM enroll2 WHERE mentee_id = '$id'";
$leaveRes3 = $mysqli->query($leaveMentorMeeting);

// remove from mentor table
$leaveMentor = "DELETE FROM mentors WHERE mentor_id = '$id'";
$leaveRes4 = $mysqli->query($leaveMentor);

$response["success"] = "true";
echo json_encode($response);
?>