<?php 

require_once('db_controller.php');
header('Content-Type: application/json');

// $value = json_decode(file_get_contents('php://input'));
// $id = $value->id;
// $jenis_hari = $value->jenis_hari;
// $tanggal = $value->tanggal; 
// $keterangan = $value->keterangan;
// $jumlah_jam = $value->jumlah_jam;
// $total_upah = $value->total_upah;

if(!empty($_POST)) {

    $id = $_POST['id'];
    $jenis_hari = $_POST['jenis_hari'];
    $tanggal = $_POST['tanggal']; 
    $keterangan = $_POST['keterangan'];
    $jumlah_jam = $_POST['jumlah_jam'];
    $total_upah = $_POST['total_upah'];

$query = $conn->prepare("SELECT * FROM datalembur WHERE id = ?");
$query->bind_param('i', $id);
$query->execute();
$result = $query->get_result();

if($result->num_rows > 0) { 

    $query = $conn -> prepare("UPDATE datalembur SET jenis_hari=?,tanggal=?,keterangan=?,jumlah_jam=?,total_upah=? WHERE id=?");
    $query -> bind_param("sssiii", $jenis_hari, $tanggal, $keterangan, $jumlah_jam, $total_upah, $id);
    $result = $query->execute();

    if($result){
        $response['message'] = $result;
    }else{
        $response['message'] = "Failed to Update";
    }
}else{
    $response['message'] = "Data not Found".$id;
}
    
}else{
    $response['message'] = "No Post Data";
}

$query -> close();
$conn -> close();

echo json_encode($response);

?>