<?php
$mysqli = new mysqli('localhost', 'root', '', 'DB2');
$email = $_POST['email'];

// get the id of the user
$getuserid = "SELECT id FROM users WHERE email = '$email'";
$idResult = $mysqli->query($getuserid);
$idArr = mysqli_fetch_array($idResult);
$id = $idArr['id'];

// get the users group to find what meetings they should see
$getGrade = "SELECT grade FROM students WHERE student_id = '$id'";
$gradeResult = $mysqli->query($getGrade);
$gradeArr = mysqli_fetch_array($gradeResult);

//get the mentee meetings that they should see
$getMeetingInfo = "SELECT * FROM meetings WHERE group_id >= ({$gradeArr['grade']})";
$meetingRes = $mysqli->query($getMeetingInfo);
$numRows=mysqli_num_rows($meetingRes);
while($meetingRow = mysqli_fetch_array($meetingRes)){ // loop through result
    if($numRows > 0){
        // join the mentee table
        $joinMentee = "INSERT INTO mentees(mentee_id) VALUES ($id)";
        $joinRes = $mysqli->query($joinMentee);

        // join all the available meetings
        $joinMenteeMeeting = "INSERT INTO enroll(meet_id, mentee_id) VALUES ({$meetingRow['meet_id']},$id)";
        $joinRes2 = $mysqli->query($joinMenteeMeeting);
    }
}

//get the mentor meetings that they should see
$getMeetingInfo2 = "SELECT * FROM meetings WHERE group_id <= ({$gradeArr['grade']}-3)";
$meetingRes2 = $mysqli->query($getMeetingInfo2);
$numRows2=mysqli_num_rows($meetingRes2);
while($meetingRow = mysqli_fetch_array($meetingRes2)){ // loop through result
    if($numRows2 > 0){
        // join the mentee table
        $joinMentor = "INSERT INTO mentors(mentor_id) VALUES ($id)";
        $joinRes3 = $mysqli->query($joinMentor);

        // join all the available meetings
        $joinMentorMeeting = "INSERT INTO enroll2(meet_id, mentor_id) VALUES ({$meetingRow['meet_id']},$id)";
        $joinRes4 = $mysqli->query($joinMentorMeeting);
    }
}

$response["success"] = "true";
echo json_encode($response);
?>