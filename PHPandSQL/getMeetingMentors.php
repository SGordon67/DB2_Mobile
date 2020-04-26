<?php
$mysqli = new mysqli('localhost', 'root', '', 'DB2');
$mid = $_POST['mid'];

// Get the mentee student ID's involved in the meeting
$GetStudentIDs = "SELECT * FROM enroll2 WHERE meet_id = '$mid'";
$studentIDsRes = $mysqli->query($GetStudentIDs);

$response = array();
$i = 0;
while($studentIDsRow = mysqli_fetch_array($studentIDsRes)){
    $getStudentInfo = "SELECT * FROM users WHERE id = {$studentIDsRow['mentor_id']}";
    $studentInfoRes = $mysqli->query($getStudentInfo);
    $studentInfoArr = mysqli_fetch_array($studentInfoRes);

    $response[strval($i) . "mid"] = $studentIDsRow['mentor_id'];
    $response[strval($i) . "mName"] = $studentInfoArr['name'];
    $response[strval($i) . "mEmail"] = $studentInfoArr['email'];
    $i = $i + 1;
}
echo json_encode($response);
?>