<html>
	<head>
		<title> Equipment Remover </title>
	</head>
	<body>

<?php
$my_dir = dirname(__FILE__);
require_once $my_dir . '\model\LaboratoryManager.php';
require_once $my_dir . '\model\Item.php';
require_once $my_dir . '\model\Equipment.php';
require_once $my_dir . '\persistence\Persistence.php';

$pm = new Persistence();
$lab = $pm->loadDataFromStore();

//Display equipment items and their attributes
for ($i = 0; $i < $lab->numberOfItems(); $i++) {
    $myEquipment = $lab->getItem_index($i);
    //Only interested in equipment class items
    if (get_class($myEquipment) != "Equipment") {
        break;
    }
    echo $myEquipment->getName();
    echo "&nbsp&nbsp&nbsp";
    echo "Base cost: ";
    echo $myEquipment->getPrice();
    echo "$";
    echo "&nbsp&nbsp&nbsp";
    echo "In use: ";
    echo $myEquipment->getQuantity();
    echo "&nbsp&nbsp&nbsp";
}
?>

<html>
	<head>
	<!-- "Delete item" submission form through GET method -->
	</head>
	<body>
		<form action="deleteItem.php" method="get">
			name:<input type="text" name="name"/>
			<input type="submit" value="submit">
			<input type="hidden" name="type" value="equipment"/>
			
			</form>
	</body>