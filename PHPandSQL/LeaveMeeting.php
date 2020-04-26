<?php
$mysqli = new mysqli('localhost', 'root', '', 'DB2');
$email = $_POST['email'];
$sMid = $_POST['mid'];
$mid = intval($sMid);
$ment = $_POST['ment'];

// get the id of the user
$getuserid = "SELECT id FROM users WHERE email = '$email'";
$idResult = $mysqli->query($getuserid);
$idArr = mysqli_fetch_array($idResult);
$id = $idArr['id'];

if($ment == "Mentee"){
    // if the user is not a mentee, no need to continue
    $isMentee = "SELECT * FROM enroll WHERE mentee_id = $id";
    $isMenteeRes = $mysqli->query($isMentee);
    $noRows = mysqli_num_rows($isMenteeRes);
    if($noRows == 0){
        // not a mentor
    }
    else{
        // If they are not a mentee of the desired meeting, stop
        $isInMeeting = "SELECT * FROM enroll WHERE mentee_id = $id AND meet_id = $mid";
        $isInMeetingRes = $mysqli->query($isInMeeting);
        $noRows2 = mysqli_num_rows($isInMeetingRes);
        if($noRows2 == 0){
            // not a mentee for this meeting
        }
        else{
            $removeFromMeeting = "DELETE FROM enroll WHERE mentee_id = $id AND meet_id = $mid";
            $removeResult = $mysqli->query($removeFromMeeting);
            if(mysqli_affected_rows($mysqli) == 1){
                $response["success"] = "true";
                echo json_encode($response);
            }
            // if the user is not a mentee for any otherr meeting, remove them from mentee table
            $isMentee2 = "SELECT * FROM enroll WHERE mentee_id = $id";
            $isMenteeRes2 = $mysqli->query($isMentee2);
            $noRows3 = mysqli_num_rows($isMenteeRes2);
            if($noRows3 == 0){
                $removeMentee = "DELETE FROM `mentees` WHERE mentee_id = $id";
                $RemoveMenteeRes = $mysqli->query($removeMentee);
            }
        }
    }
} else {
    // if youre trying to leave a mentor meeting
    // if the user is not a mentor, no need to continue
    $ismentor = "SELECT * FROM enroll2 WHERE mentor_id = $id";
    $ismentorRes = $mysqli->query($ismentor);
    if($ismentorRes){
        $noRows = mysqli_num_rows($ismentorRes);
        if($noRows == 0){
            // not a mentor
        }
        else{
            // If they are not a mentor of the desired meeting, stop
            $isInMeeting = "SELECT * FROM enroll2 WHERE mentor_id = $id AND meet_id = $mid";
            $isInMeetingRes = $mysqli->query($isInMeeting);
            $noRows2 = mysqli_num_rows($isInMeetingRes);
            if($noRows2 == 0){
                // not a mentor for this meeting
            }
            else{
                $removeFromMeeting = "DELETE FROM enroll2 WHERE mentor_id = $id AND meet_id = $mid";
                $removeResult = $mysqli->query($removeFromMeeting);
                if(mysqli_affected_rows($mysqli) == 1){
                    $response["success"] = "true";
                    echo json_encode($response);
                }
                // if the user is not a mentor for any otherr meeting, remove them from mentor table
                $ismentor2 = "SELECT * FROM enroll2 WHERE mentor_id = $id";
                $ismentorRes2 = $mysqli->query($ismentor2);
                $noRows3 = mysqli_num_rows($ismentorRes2);
                if($noRows3 == 0){
                    $removementor = "DELETE FROM `mentors` WHERE mentor_id = $id";
                    $RemovementorRes = $mysqli->query($removementor);
                }
            }
        }
    }
}


$response["success"] = "true";
echo json_encode($response);
?>