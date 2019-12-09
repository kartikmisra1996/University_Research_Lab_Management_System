<html>
	<head>
		<title> Supply Manager</title>
	</head>
		<body>

<?php
$my_dir = dirname(__FILE__);
require_once $my_dir . '\model\LaboratoryManager.php';
require_once $my_dir . '\model\Item.php';
require_once $my_dir . '\model\Supply.php';
require_once $my_dir . '\model\Equipment.php';
require_once $my_dir . '\persistence\Persistence.php';

$pm = new Persistence();
$lab = $pm->loadDataFromStore();

//Display supplies and their attributes
for ($i = 0; $i < $lab->numberOfItems(); $i++) {
    $mySupply = $lab->getItem_index($i);
    if (get_class($mySupply) == "Supply") {
        
    echo $mySupply->getName();
    echo "&nbsp&nbsp&nbsp";
    echo "Base cost: ";
    echo $mySupply->getPrice();
    echo "$";
    echo "&nbsp&nbsp&nbsp";
    echo "Remaining: ";
    echo $mySupply->getQuantity();
    echo "&nbsp&nbsp&nbsp";
    if ($mySupply->getIsAvailable()) {
        echo "Available";
    } else {
        echo "Not available";
    }
    echo "&nbsp&nbsp&nbsp";
    
    //Close php brackets within the loop to use html GET form submissions
    ?>
    
    <form action="itemModifier.php" method="get">
			<input type="hidden" name="index" value="<?php echo htmlspecialchars($i); ?>"/>
			<input type="submit" value="Edit">
			</form>
	<form action="useSupply.php" method="get">
			<input type="hidden" name="index" value="<?php echo htmlspecialchars($i); ?>"/>
			<input type="submit" value="Use 1 of this supply">
			</form>
	<?php
	//Reopen php brackets before the end of the loop to iterate through next loop
    
    echo '<br><br>';
}
}
?>
	    <!-- "Add Supply" submission form through GET method -->
		<form action="addSupply.php" method="get">
			Name:<input type="text" name="name"/>
			Base Price:<input type="text" name="price"/>
			Quantity:<input type="text" name="quantity"/>
			<input type="submit" value="submit">
			</form>
		    <!-- Link to Remove Supply page -->
			<a href="supplyRemover.php">Delete a supply item</a><br>
			
	</body>