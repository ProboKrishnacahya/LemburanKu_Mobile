<?php 

require_once('db_controller.php');
header('Content-Type: application/json');

$value = json_decode(file_get_contents('php://input'));
$id = $value->id;

$query = $conn->prepare("SELECT * FROM datalembur WHERE id = ?");
$query->bind_param('i', $id);
$query->execute();

$result = $query->get_result();
$data = $result->fetch_assoc();

if(!empty($data)) {
    $object = array(
        'id' => $data['id'],
        'jenis_hari' => $data['jenis_hari'],
        'tanggal' => $data['tanggal'],
        'keterangan' => $data['keterangan'],
        'jumlah_jam' => $data['jumlah_jam'],
        'total_upah' => $data['total_upah']
    );

    $response["data"] = $object;
}else{
    $response["Message"] = "Data not found";
}

$query->close();
$conn->close();

echo json_encode($response);

?>