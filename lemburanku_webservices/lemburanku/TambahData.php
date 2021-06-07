<?php 

require_once('db_controller.php');
header('Content-Type: application/json');

if(!empty($_POST)) {

    $jenis_hari = $_POST['jenis_hari'];
    $tanggal = $_POST['tanggal']; 
    $keterangan = $_POST['keterangan'];
    $jumlah_jam = $_POST['jumlah_jam'];
    $total_upah = $_POST['total_upah'];

    $query = $conn -> prepare("INSERT INTO datalembur(jenis_hari, tanggal, keterangan, jumlah_jam, total_upah) VALUES (?,?,?,?,?)");
    $query -> bind_param("sssii", $jenis_hari, $tanggal, $keterangan, $jumlah_jam, $total_upah);
    $result = $query->execute();

    if($result){
        $response['message'] = "Data Created";
    }else{
        $response['message'] = "Failed to save";
    }
    
}else{
    $response['message'] = "No Post Data";
}

$query -> close();
$conn -> close();

echo json_encode($response);

?>