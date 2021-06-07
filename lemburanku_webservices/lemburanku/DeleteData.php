<?php 

require_once('db_controller.php');
header('Content-Type: application/json');

// if(!empty($_POST)) {
//     $id = $_POST['id'];
$value = json_decode(file_get_contents('php://input'));
$id = $value->id;
    
$query = $conn->prepare("SELECT * FROM datalembur WHERE id = ?");
$query->bind_param('i', $id);
$query->execute();
$result = $query->get_result();

if($result->num_rows > 0) {

    $query = $conn -> prepare("DELETE FROM datalembur WHERE id=?");
    $query -> bind_param("i", $id);
    $result = $query->execute();

    if($result){
        $response['message'] = $result;
    }else{
        $response['message'] = "Failed to Delete";
    }
}else{
    $response['message'] = "Data not Found";
}
    
// }else{
//     $response['message'] = "No Post Data";
// }

$query -> close();
$conn -> close();

echo json_encode($response);

?>                      