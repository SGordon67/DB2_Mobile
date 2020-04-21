<?php
$mysqli = new mysqli('localhost', 'root', '', 'DB2');
$email = $_POST['email'];

// get the id of the user
$getuserid = "SELECT id, name FROM users WHERE email = '$email'";
$idResult = $mysqli->query($getuserid);
$idArr = mysqli_fetch_array($idResult);
$id = $idArr['id'];

// get all study materials for meetings for this user (mentee or mentor)
$getMeetings = "SELECT meet_id FROM enroll WHERE mentee_id = '$id' UNION SELECT meet_id FROM enroll2 WHERE mentor_id = '$id'";
$meetingsRes = $mysqli->query($getMeetings);

$response = array();
$i = 0;
while($meetingArr = mysqli_fetch_array($meetingsRes)){
    // get all the meeting ID's from enroll table and enroll2 table
    $getMaterialID = "SELECT * FROM assign WHERE meet_id = {$meetingArr['meet_id']}";
    $materialRes = $mysqli->query($getMaterialID);
    while($materialIDArr = mysqli_fetch_array($materialRes)){
        // getting the material information, mainly the url
        $getMaterialInfo = "SELECT * FROM material WHERE material_id = {$materialIDArr['material_id']}";
        $materialRes2 = $mysqli->query($getMaterialInfo);
        while($materialInfoArr = mysqli_fetch_array($materialRes2)){
            
            $response[strval($i) . "mid"] = $meetingArr['meet_id'];
            $response[strval($i) . "mUrl"] = $materialInfoArr['url'];
            $i = $i + 1;
        }
    }
}
echo json_encode($response);
?>