<?php 

require_once('db_controller.php');
header('Content-Type: application/json');

$query = $conn -> query ("SELECT * FROM datalembur");

$response["count"] = $query->num_rows;
$response["data"] = array();

while($data = mysqli_fetch_assoc($query)){
    $object = array(
        'id' => $data['id'],
        'jenis_hari' => $data['jenis_hari'],
        'tanggal' => $data['tanggal'],
        'keterangan' => $data['keterangan'],
        'jumlah_jam' => $data['jumlah_jam'],
        'total_upah' => $data['total_upah']
    );

    array_push($response["data"], $object);
};

$query->close();
$conn->close();

echo json_encode($response);

?>