idiagram UsedCarBuyer
{
   title = "Conversion a partir de formato IctNeo";
   author = "Manuel Gomez";
   default node states = (ausente presente);

//Description of NODES

node NetValue (continuous) {
    kind-of-node = utility;
    min = 0.000000;
    max = 1031.000000;
}

node Cars_Conditions {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  Peach  Lemon );
}

node SecondTestResults {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  NoResults  Defects0  Defects1 );
}

node FirstTestResults {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  NoResults  Defects0  Defects1  Defects2 );
}

node PurchaseDecision {
    kind-of-node = decision;
    type-of-variable = finite-states;
    states = (  No  Yes );
}

node SecondTestDecision {
    kind-of-node = decision;
    type-of-variable = finite-states;
    states = (  NoTest  Differential );
}

node FirstTestDecision {
    kind-of-node = decision;
    type-of-variable = finite-states;
    states = (  NoTest  Steering  Transmission  FuelElectrical );
}

//Description of LINKS

link FirstTestDecision NetValue;

link SecondTestDecision NetValue;

link PurchaseDecision NetValue;

link Cars_Conditions NetValue;

link FirstTestDecision SecondTestResults;

link FirstTestResults SecondTestResults;

link SecondTestDecision SecondTestResults;

link Cars_Conditions SecondTestResults;

link FirstTestDecision FirstTestResults;

link Cars_Conditions FirstTestResults;

link FirstTestDecision PurchaseDecision;

link FirstTestResults PurchaseDecision;

link SecondTestDecision PurchaseDecision;

link SecondTestResults PurchaseDecision;

link FirstTestDecision SecondTestDecision;

link FirstTestResults SecondTestDecision;

//Description of RELATIONS


relation NetValue FirstTestDecision SecondTestDecision PurchaseDecision Cars_Conditions 
{
   values=table(
               [ NoTest NoTest No Peach ] = 800.000000,
               [ NoTest NoTest No Lemon ] = 940.000000,
               [ NoTest NoTest Yes Peach ] = 1000.000000,
               [ NoTest NoTest Yes Lemon ] = 1000.000000,
               [ NoTest Differential No Peach ] = 0.000000,
               [ NoTest Differential No Lemon ] = 0.000000,
               [ NoTest Differential Yes Peach ] = 0.000000,
               [ NoTest Differential Yes Lemon ] = 0.000000,
               [ Steering NoTest No Peach ] = 891.000000,
               [ Steering NoTest No Lemon ] = 1031.000000,
               [ Steering NoTest Yes Peach ] = 1000.000000,
               [ Steering NoTest Yes Lemon ] = 1000.000000,
               [ Steering Differential No Peach ] = 0.000000,
               [ Steering Differential No Lemon ] = 0.000000,
               [ Steering Differential Yes Peach ] = 0.000000,
               [ Steering Differential Yes Lemon ] = 0.000000,
               [ Transmission NoTest No Peach ] = 890.000000,
               [ Transmission NoTest No Lemon ] = 1030.000000,
               [ Transmission NoTest Yes Peach ] = 1000.000000,
               [ Transmission NoTest Yes Lemon ] = 1000.000000,
               [ Transmission Differential No Peach ] = 886.000000,
               [ Transmission Differential No Lemon ] = 10.000000,
               [ Transmission Differential Yes Peach ] = 1000.000000,
               [ Transmission Differential Yes Lemon ] = 1000.000000,
               [ FuelElectrical NoTest No Peach ] = 887.000000,
               [ FuelElectrical NoTest No Lemon ] = 1027.000000,
               [ FuelElectrical NoTest Yes Peach ] = 1000.000000,
               [ FuelElectrical NoTest Yes Lemon ] = 1000.000000,
               [ FuelElectrical Differential No Peach ] = 0.000000,
               [ FuelElectrical Differential No Lemon ] = 0.000000,
               [ FuelElectrical Differential Yes Peach ] = 0.000000,
               [ FuelElectrical Differential Yes Lemon ] = 0.000000);
}

relation Cars_Conditions 
{
   values=table(
               [ Peach ] = 0.800000,
               [ Lemon ] = 0.200000);
}

relation SecondTestResults FirstTestDecision FirstTestResults SecondTestDecision Cars_Conditions 
{
   values=table(
               [ NoResults NoTest NoResults NoTest Peach ] = 1.000000,
               [ NoResults NoTest NoResults NoTest Lemon ] = 1.000000,
               [ NoResults NoTest NoResults Differential Peach ] = 1.000000,
               [ NoResults NoTest NoResults Differential Lemon ] = 1.000000,
               [ NoResults NoTest Defects0 NoTest Peach ] = 1.000000,
               [ NoResults NoTest Defects0 NoTest Lemon ] = 1.000000,
               [ NoResults NoTest Defects0 Differential Peach ] = 1.000000,
               [ NoResults NoTest Defects0 Differential Lemon ] = 1.000000,
               [ NoResults NoTest Defects1 NoTest Peach ] = 1.000000,
               [ NoResults NoTest Defects1 NoTest Lemon ] = 1.000000,
               [ NoResults NoTest Defects1 Differential Peach ] = 1.000000,
               [ NoResults NoTest Defects1 Differential Lemon ] = 1.000000,
               [ NoResults NoTest Defects2 NoTest Peach ] = 1.000000,
               [ NoResults NoTest Defects2 NoTest Lemon ] = 1.000000,
               [ NoResults NoTest Defects2 Differential Peach ] = 1.000000,
               [ NoResults NoTest Defects2 Differential Lemon ] = 1.000000,
               [ NoResults Steering NoResults NoTest Peach ] = 1.000000,
               [ NoResults Steering NoResults NoTest Lemon ] = 1.000000,
               [ NoResults Steering NoResults Differential Peach ] = 1.000000,
               [ NoResults Steering NoResults Differential Lemon ] = 1.000000,
               [ NoResults Steering Defects0 NoTest Peach ] = 1.000000,
               [ NoResults Steering Defects0 NoTest Lemon ] = 1.000000,
               [ NoResults Steering Defects0 Differential Peach ] = 1.000000,
               [ NoResults Steering Defects0 Differential Lemon ] = 1.000000,
               [ NoResults Steering Defects1 NoTest Peach ] = 1.000000,
               [ NoResults Steering Defects1 NoTest Lemon ] = 1.000000,
               [ NoResults Steering Defects1 Differential Peach ] = 1.000000,
               [ NoResults Steering Defects1 Differential Lemon ] = 1.000000,
               [ NoResults Steering Defects2 NoTest Peach ] = 1.000000,
               [ NoResults Steering Defects2 NoTest Lemon ] = 1.000000,
               [ NoResults Steering Defects2 Differential Peach ] = 1.000000,
               [ NoResults Steering Defects2 Differential Lemon ] = 1.000000,
               [ NoResults Transmission NoResults NoTest Peach ] = 1.000000,
               [ NoResults Transmission NoResults NoTest Lemon ] = 1.000000,
               [ NoResults Transmission NoResults Differential Peach ] = 1.000000,
               [ NoResults Transmission NoResults Differential Lemon ] = 1.000000,
               [ NoResults Transmission Defects0 NoTest Peach ] = 1.000000,
               [ NoResults Transmission Defects0 NoTest Lemon ] = 1.000000,
               [ NoResults Transmission Defects0 Differential Peach ] = 0.000000,
               [ NoResults Transmission Defects0 Differential Lemon ] = 0.000000,
               [ NoResults Transmission Defects1 NoTest Peach ] = 1.000000,
               [ NoResults Transmission Defects1 NoTest Lemon ] = 1.000000,
               [ NoResults Transmission Defects1 Differential Peach ] = 0.000000,
               [ NoResults Transmission Defects1 Differential Lemon ] = 0.000000,
               [ NoResults Transmission Defects2 NoTest Peach ] = 1.000000,
               [ NoResults Transmission Defects2 NoTest Lemon ] = 1.000000,
               [ NoResults Transmission Defects2 Differential Peach ] = 1.000000,
               [ NoResults Transmission Defects2 Differential Lemon ] = 1.000000,
               [ NoResults FuelElectrical NoResults NoTest Peach ] = 1.000000,
               [ NoResults FuelElectrical NoResults NoTest Lemon ] = 1.000000,
               [ NoResults FuelElectrical NoResults Differential Peach ] = 1.000000,
               [ NoResults FuelElectrical NoResults Differential Lemon ] = 1.000000,
               [ NoResults FuelElectrical Defects0 NoTest Peach ] = 1.000000,
               [ NoResults FuelElectrical Defects0 NoTest Lemon ] = 1.000000,
               [ NoResults FuelElectrical Defects0 Differential Peach ] = 1.000000,
               [ NoResults FuelElectrical Defects0 Differential Lemon ] = 1.000000,
               [ NoResults FuelElectrical Defects1 NoTest Peach ] = 1.000000,
               [ NoResults FuelElectrical Defects1 NoTest Lemon ] = 1.000000,
               [ NoResults FuelElectrical Defects1 Differential Peach ] = 1.000000,
               [ NoResults FuelElectrical Defects1 Differential Lemon ] = 1.000000,
               [ NoResults FuelElectrical Defects2 NoTest Peach ] = 1.000000,
               [ NoResults FuelElectrical Defects2 NoTest Lemon ] = 1.000000,
               [ NoResults FuelElectrical Defects2 Differential Peach ] = 1.000000,
               [ NoResults FuelElectrical Defects2 Differential Lemon ] = 1.000000,
               [ Defects0 NoTest NoResults NoTest Peach ] = 0.000000,
               [ Defects0 NoTest NoResults NoTest Lemon ] = 0.000000,
               [ Defects0 NoTest NoResults Differential Peach ] = 0.000000,
               [ Defects0 NoTest NoResults Differential Lemon ] = 0.000000,
               [ Defects0 NoTest Defects0 NoTest Peach ] = 0.000000,
               [ Defects0 NoTest Defects0 NoTest Lemon ] = 0.000000,
               [ Defects0 NoTest Defects0 Differential Peach ] = 0.000000,
               [ Defects0 NoTest Defects0 Differential Lemon ] = 0.000000,
               [ Defects0 NoTest Defects1 NoTest Peach ] = 0.000000,
               [ Defects0 NoTest Defects1 NoTest Lemon ] = 0.000000,
               [ Defects0 NoTest Defects1 Differential Peach ] = 0.000000,
               [ Defects0 NoTest Defects1 Differential Lemon ] = 0.000000,
               [ Defects0 NoTest Defects2 NoTest Peach ] = 0.000000,
               [ Defects0 NoTest Defects2 NoTest Lemon ] = 0.000000,
               [ Defects0 NoTest Defects2 Differential Peach ] = 0.000000,
               [ Defects0 NoTest Defects2 Differential Lemon ] = 0.000000,
               [ Defects0 Steering NoResults NoTest Peach ] = 0.000000,
               [ Defects0 Steering NoResults NoTest Lemon ] = 0.000000,
               [ Defects0 Steering NoResults Differential Peach ] = 0.000000,
               [ Defects0 Steering NoResults Differential Lemon ] = 0.000000,
               [ Defects0 Steering Defects0 NoTest Peach ] = 0.000000,
               [ Defects0 Steering Defects0 NoTest Lemon ] = 0.000000,
               [ Defects0 Steering Defects0 Differential Peach ] = 0.000000,
               [ Defects0 Steering Defects0 Differential Lemon ] = 0.000000,
               [ Defects0 Steering Defects1 NoTest Peach ] = 0.000000,
               [ Defects0 Steering Defects1 NoTest Lemon ] = 0.000000,
               [ Defects0 Steering Defects1 Differential Peach ] = 0.000000,
               [ Defects0 Steering Defects1 Differential Lemon ] = 0.000000,
               [ Defects0 Steering Defects2 NoTest Peach ] = 0.000000,
               [ Defects0 Steering Defects2 NoTest Lemon ] = 0.000000,
               [ Defects0 Steering Defects2 Differential Peach ] = 0.000000,
               [ Defects0 Steering Defects2 Differential Lemon ] = 0.000000,
               [ Defects0 Transmission NoResults NoTest Peach ] = 0.000000,
               [ Defects0 Transmission NoResults NoTest Lemon ] = 0.000000,
               [ Defects0 Transmission NoResults Differential Peach ] = 0.000000,
               [ Defects0 Transmission NoResults Differential Lemon ] = 0.000000,
               [ Defects0 Transmission Defects0 NoTest Peach ] = 0.000000,
               [ Defects0 Transmission Defects0 NoTest Lemon ] = 0.000000,
               [ Defects0 Transmission Defects0 Differential Peach ] = 0.890000,
               [ Defects0 Transmission Defects0 Differential Lemon ] = 0.670000,
               [ Defects0 Transmission Defects1 NoTest Peach ] = 0.000000,
               [ Defects0 Transmission Defects1 NoTest Lemon ] = 0.000000,
               [ Defects0 Transmission Defects1 Differential Peach ] = 0.440000,
               [ Defects0 Transmission Defects1 Differential Lemon ] = 1.000000,
               [ Defects0 Transmission Defects2 NoTest Peach ] = 0.000000,
               [ Defects0 Transmission Defects2 NoTest Lemon ] = 0.000000,
               [ Defects0 Transmission Defects2 Differential Peach ] = 0.000000,
               [ Defects0 Transmission Defects2 Differential Lemon ] = 0.000000,
               [ Defects0 FuelElectrical NoResults NoTest Peach ] = 0.000000,
               [ Defects0 FuelElectrical NoResults NoTest Lemon ] = 0.000000,
               [ Defects0 FuelElectrical NoResults Differential Peach ] = 0.000000,
               [ Defects0 FuelElectrical NoResults Differential Lemon ] = 0.000000,
               [ Defects0 FuelElectrical Defects0 NoTest Peach ] = 0.000000,
               [ Defects0 FuelElectrical Defects0 NoTest Lemon ] = 0.000000,
               [ Defects0 FuelElectrical Defects0 Differential Peach ] = 0.000000,
               [ Defects0 FuelElectrical Defects0 Differential Lemon ] = 0.000000,
               [ Defects0 FuelElectrical Defects1 NoTest Peach ] = 0.000000,
               [ Defects0 FuelElectrical Defects1 NoTest Lemon ] = 0.000000,
               [ Defects0 FuelElectrical Defects1 Differential Peach ] = 0.000000,
               [ Defects0 FuelElectrical Defects1 Differential Lemon ] = 0.000000,
               [ Defects0 FuelElectrical Defects2 NoTest Peach ] = 0.000000,
               [ Defects0 FuelElectrical Defects2 NoTest Lemon ] = 0.000000,
               [ Defects0 FuelElectrical Defects2 Differential Peach ] = 0.000000,
               [ Defects0 FuelElectrical Defects2 Differential Lemon ] = 0.000000,
               [ Defects1 NoTest NoResults NoTest Peach ] = 0.000000,
               [ Defects1 NoTest NoResults NoTest Lemon ] = 0.000000,
               [ Defects1 NoTest NoResults Differential Peach ] = 0.000000,
               [ Defects1 NoTest NoResults Differential Lemon ] = 0.000000,
               [ Defects1 NoTest Defects0 NoTest Peach ] = 0.000000,
               [ Defects1 NoTest Defects0 NoTest Lemon ] = 0.000000,
               [ Defects1 NoTest Defects0 Differential Peach ] = 0.000000,
               [ Defects1 NoTest Defects0 Differential Lemon ] = 0.000000,
               [ Defects1 NoTest Defects1 NoTest Peach ] = 0.000000,
               [ Defects1 NoTest Defects1 NoTest Lemon ] = 0.000000,
               [ Defects1 NoTest Defects1 Differential Peach ] = 0.000000,
               [ Defects1 NoTest Defects1 Differential Lemon ] = 0.000000,
               [ Defects1 NoTest Defects2 NoTest Peach ] = 0.000000,
               [ Defects1 NoTest Defects2 NoTest Lemon ] = 0.000000,
               [ Defects1 NoTest Defects2 Differential Peach ] = 0.000000,
               [ Defects1 NoTest Defects2 Differential Lemon ] = 0.000000,
               [ Defects1 Steering NoResults NoTest Peach ] = 0.000000,
               [ Defects1 Steering NoResults NoTest Lemon ] = 0.000000,
               [ Defects1 Steering NoResults Differential Peach ] = 0.000000,
               [ Defects1 Steering NoResults Differential Lemon ] = 0.000000,
               [ Defects1 Steering Defects0 NoTest Peach ] = 0.000000,
               [ Defects1 Steering Defects0 NoTest Lemon ] = 0.000000,
               [ Defects1 Steering Defects0 Differential Peach ] = 0.000000,
               [ Defects1 Steering Defects0 Differential Lemon ] = 0.000000,
               [ Defects1 Steering Defects1 NoTest Peach ] = 0.000000,
               [ Defects1 Steering Defects1 NoTest Lemon ] = 0.000000,
               [ Defects1 Steering Defects1 Differential Peach ] = 0.000000,
               [ Defects1 Steering Defects1 Differential Lemon ] = 0.000000,
               [ Defects1 Steering Defects2 NoTest Peach ] = 0.000000,
               [ Defects1 Steering Defects2 NoTest Lemon ] = 0.000000,
               [ Defects1 Steering Defects2 Differential Peach ] = 0.000000,
               [ Defects1 Steering Defects2 Differential Lemon ] = 0.000000,
               [ Defects1 Transmission NoResults NoTest Peach ] = 0.000000,
               [ Defects1 Transmission NoResults NoTest Lemon ] = 0.000000,
               [ Defects1 Transmission NoResults Differential Peach ] = 0.000000,
               [ Defects1 Transmission NoResults Differential Lemon ] = 0.000000,
               [ Defects1 Transmission Defects0 NoTest Peach ] = 0.000000,
               [ Defects1 Transmission Defects0 NoTest Lemon ] = 0.000000,
               [ Defects1 Transmission Defects0 Differential Peach ] = 0.110000,
               [ Defects1 Transmission Defects0 Differential Lemon ] = 0.330000,
               [ Defects1 Transmission Defects1 NoTest Peach ] = 0.000000,
               [ Defects1 Transmission Defects1 NoTest Lemon ] = 0.000000,
               [ Defects1 Transmission Defects1 Differential Peach ] = 0.560000,
               [ Defects1 Transmission Defects1 Differential Lemon ] = 0.000000,
               [ Defects1 Transmission Defects2 NoTest Peach ] = 0.000000,
               [ Defects1 Transmission Defects2 NoTest Lemon ] = 0.000000,
               [ Defects1 Transmission Defects2 Differential Peach ] = 0.000000,
               [ Defects1 Transmission Defects2 Differential Lemon ] = 0.000000,
               [ Defects1 FuelElectrical NoResults NoTest Peach ] = 0.000000,
               [ Defects1 FuelElectrical NoResults NoTest Lemon ] = 0.000000,
               [ Defects1 FuelElectrical NoResults Differential Peach ] = 0.000000,
               [ Defects1 FuelElectrical NoResults Differential Lemon ] = 0.000000,
               [ Defects1 FuelElectrical Defects0 NoTest Peach ] = 0.000000,
               [ Defects1 FuelElectrical Defects0 NoTest Lemon ] = 0.000000,
               [ Defects1 FuelElectrical Defects0 Differential Peach ] = 0.000000,
               [ Defects1 FuelElectrical Defects0 Differential Lemon ] = 0.000000,
               [ Defects1 FuelElectrical Defects1 NoTest Peach ] = 0.000000,
               [ Defects1 FuelElectrical Defects1 NoTest Lemon ] = 0.000000,
               [ Defects1 FuelElectrical Defects1 Differential Peach ] = 0.000000,
               [ Defects1 FuelElectrical Defects1 Differential Lemon ] = 0.000000,
               [ Defects1 FuelElectrical Defects2 NoTest Peach ] = 0.000000,
               [ Defects1 FuelElectrical Defects2 NoTest Lemon ] = 0.000000,
               [ Defects1 FuelElectrical Defects2 Differential Peach ] = 0.000000,
               [ Defects1 FuelElectrical Defects2 Differential Lemon ] = 0.000000);
}

relation FirstTestResults FirstTestDecision Cars_Conditions 
{
   values=table(
               [ NoResults NoTest Peach ] = 1.000000,
               [ NoResults NoTest Lemon ] = 1.000000,
               [ NoResults Steering Peach ] = 0.000000,
               [ NoResults Steering Lemon ] = 0.000000,
               [ NoResults Transmission Peach ] = 0.000000,
               [ NoResults Transmission Lemon ] = 0.000000,
               [ NoResults FuelElectrical Peach ] = 0.000000,
               [ NoResults FuelElectrical Lemon ] = 0.000000,
               [ Defects0 NoTest Peach ] = 0.000000,
               [ Defects0 NoTest Lemon ] = 0.000000,
               [ Defects0 Steering Peach ] = 0.900000,
               [ Defects0 Steering Lemon ] = 0.400000,
               [ Defects0 Transmission Peach ] = 0.900000,
               [ Defects0 Transmission Lemon ] = 0.400000,
               [ Defects0 FuelElectrical Peach ] = 0.800000,
               [ Defects0 FuelElectrical Lemon ] = 0.130000,
               [ Defects1 NoTest Peach ] = 0.000000,
               [ Defects1 NoTest Lemon ] = 0.000000,
               [ Defects1 Steering Peach ] = 0.100000,
               [ Defects1 Steering Lemon ] = 0.600000,
               [ Defects1 Transmission Peach ] = 0.100000,
               [ Defects1 Transmission Lemon ] = 0.600000,
               [ Defects1 FuelElectrical Peach ] = 0.200000,
               [ Defects1 FuelElectrical Lemon ] = 0.530000,
               [ Defects2 NoTest Peach ] = 0.000000,
               [ Defects2 NoTest Lemon ] = 0.000000,
               [ Defects2 Steering Peach ] = 0.000000,
               [ Defects2 Steering Lemon ] = 0.000000,
               [ Defects2 Transmission Peach ] = 0.000000,
               [ Defects2 Transmission Lemon ] = 0.000000,
               [ Defects2 FuelElectrical Peach ] = 0.000000,
               [ Defects2 FuelElectrical Lemon ] = 0.340000);
}

relation FirstTestDecision FirstTestResults
{
  kind-of-relation=constraint;
  values=logical-expression(FirstTestDecision in {NoTest} <-> FirstTestResults in {NoResults});
}

relation FirstTestDecision FirstTestResults
{
  kind-of-relation=constraint;
  values=logical-expression(FirstTestResults in {Defects2} -> FirstTestDecision in {FuelElectrical});
}


relation SecondTestDecision SecondTestResults
{
  kind-of-relation=constraint;
  values=logical-expression(SecondTestDecision in {NoTest} <-> SecondTestResults in {NoResults});
}

}
