<?php
$mysqli = new mysqli('localhost', 'root', '', 'DB2');
$email = $_POST['email'];

// get the id of the user
$getuserid = "SELECT id FROM users WHERE email = '$email'";
$idResult = $mysqli->query($getuserid);
$idArr = mysqli_fetch_array($idResult);

// get the users group to find what meetings they should see
$getGrade = "SELECT grade FROM students WHERE student_id = {$idArr['id']}";
$gradeResult = $mysqli->query($getGrade);
$gradeArr = mysqli_fetch_array($gradeResult);

//get the meetings that they should see
$getMeetingInfo = "SELECT * FROM meetings WHERE group_id >= ({$gradeArr['grade']})";
$result = $mysqli->query($getMeetingInfo);

$response = array();
$i = 0;
while($row = mysqli_fetch_array($result)){
    // get the time information for meeting
    $getMeetingTime = "SELECT start_time, end_time FROM time_slot WHERE time_slot_id = {$row['time_slot_id']}";
    $timeResult = $mysqli->query($getMeetingTime);
    $timeArr = mysqli_fetch_array($timeResult);

    $response[strval($i) . "mName"] = $row['meet_name'];
    $response[strval($i) . "mDate"] = $row['date'];
    $response[strval($i) . "mStartTime"] = $timeArr['start_time'];
    $response[strval($i) . "mEndTime"] = $timeArr['end_time'];
    $response[strval($i) . "mid"] = $row['meet_id'];
    $i = $i + 1;
}

echo json_encode($response);
?>