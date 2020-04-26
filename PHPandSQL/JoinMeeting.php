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

// if youre trying to mentee for a meeting
if($ment == "Mentee"){
    $testJoined = "SELECT * FROM enroll WHERE mentee_id = $id AND meet_id = $mid";
    $testJoinedRes = $mysqli->query($testJoined);
    $noRows = mysqli_num_rows($testJoinedRes);
    if($noRows == 1){
        //already in meeting
    }
    else{
        // make sure the user a mentee
        $isMentee = "SELECT * FROM enroll WHERE mentee_id = $id";
        $isMenteeRes = $mysqli->query($isMentee);
        $noRows2 = mysqli_num_rows($isMenteeRes);
        if($noRows2 == 0){
            $makeMentee = "INSERT INTO mentees(mentee_id) VALUES ($id)";
            $MenteeRes = $mysqli->query($makeMentee);
        }

        // 
        $isMentee2 = "SELECT * FROM mentees WHERE mentee_id = $id";
        if(!$isMentee2){
            // error joining
        } else {
            // add them to the meeting
            $joinQuery = "INSERT INTO enroll(meet_id, mentee_id) VALUES ($mid,$id)";
            $joinRes = $mysqli->query($joinQuery);
            if($joinRes){
                $response["success"] = "true";
                echo json_encode($response);
            } else {
                // if failed, and they are not a mentee in another meeting, remove them from mentee
                $isMentee2 = "SELECT * FROM enroll WHERE mentee_id = $id";
                $isMenteeRes2 = $mysqli->query($isMentee2);
                $noRows3 = mysqli_num_rows($isMenteeRes2);
                if($noRows3 == 0){
                    $removeMentee = "DELETE FROM `mentees` WHERE mentee_id = $id ";
                    $RemoveMenteeRes = $mysqli->query($removeMentee);
                }
            }
        }
    }
} else {
    // if youre trying to mentor for a meeting
    $testJoined = "SELECT * FROM enroll2 WHERE mentor_id = $id AND meet_id = $mid";
    $testJoinedRes = $mysqli->query($testJoined);
    $noRows = mysqli_num_rows($testJoinedRes);
    if($noRows == 1){
        echo "Already Joined Meeting";
    }
    else{
        // make sure the user a mentor
        $ismentor = "SELECT * FROM enroll2 WHERE mentor_id = $id";
        $ismentorRes = $mysqli->query($ismentor);
        $noRows2 = mysqli_num_rows($ismentorRes);
        if($noRows2 == 0){
            $makementor = "INSERT INTO mentors(mentor_id) VALUES ($id)";
            $mentorRes = $mysqli->query($makementor);
        }

        // 
        $ismentor2 = "SELECT * FROM mentors WHERE mentor_id = $id";
        if(!$ismentor2){
            // error Joining
        } else {
            // add them to the meeting
            $joinQuery = "INSERT INTO enroll2(meet_id, mentor_id) VALUES ($mid,$id)";
            $joinRes = $mysqli->query($joinQuery);
            if($joinRes){
                $response["success"] = "true";
                echo json_encode($response);
            } else {
                // if failed, and they are not a mentor in another meeting, remove them from mentor
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
?>