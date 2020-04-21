<?php
$mysqli = new mysqli('localhost', 'root', '', 'DB2');
$email = $_POST['email'];

// get the id of the user
$getuserid = "SELECT id FROM users WHERE email = '$email'";
$idResult = $mysqli->query($getuserid);
$idArr = mysqli_fetch_array($idResult);

// get the meeting id's
$getMentorMeetings = "SELECT meet_id FROM enroll2 WHERE mentor_id = {$idArr['id']}";
$MentorMeetingsResult = $mysqli->query($getMentorMeetings);

$response = array();
$i = 0;
while($MentorMeetingsArr = mysqli_fetch_array($MentorMeetingsResult)){
    //get the information about the meeting
    $getMeetingInfo = "SELECT * FROM meetings WHERE meet_id = {$MentorMeetingsArr['meet_id']}";
    $result = $mysqli->query($getMeetingInfo);
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
}

echo json_encode($response);
?>