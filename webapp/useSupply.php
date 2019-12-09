<?php
//This class is used by the user to quickly deduct a supply's quantity by 1

$my_dir = dirname(__FILE__);
require_once $my_dir . '/controller/Controller.php';

session_start();

$itemindex= $_GET['index'];


$pm = new Persistence();
$lab = $pm->loadDataFromStore();

$c = new Controller();
try {
    $c->useSupply($itemindex);
    $_SESSION["errorItemName"] = "";
} catch (Exception $e) {
    $_SESSION["errorItemName"] = $e->getMessage();
}
?>

<html>
<head>
<meta charset="UTF-8">
        <meta http-equiv="refresh" content="1; url=manageEmployees.php">

<?php

echo "<script type='text/javascript'>";

echo "window.location.href = 'manageSupplies.php'";

   echo "</script>"; ?>
    <title>Page Redirection</title>
    </head>
    
    
    </html>