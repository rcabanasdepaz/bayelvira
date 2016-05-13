// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x8_1 x8_2 x8_3);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
}

node D1(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =2;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 4;
states = (d1_1 d1_2 d1_3 d1_4);
}

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x4_1 x4_2);
}

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x5_1 x5_2 x5_3);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node D2(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =5;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 4;
states = (d2_1 d2_2 d2_3 d2_4);
}

node X1(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 4;
states = (x1_1 x1_2 x1_3 x1_4);
}

node X2(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x2_1 x2_2);
}

node X3(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x3_1 x3_2 x3_3);
}

node V0(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_x =3;
pos_y =5;
relevance = 7.0;
purpose = "";
min = 0;
max = 1;
precision = 2;
}

// Links of the associated graph:

link D1 D2;

link D1 V0;

link D1 X2;

link D1 X3;

link D1 X4;

link D1 X6;

link D2 V0;

link D2 X2;

link X1 X3;

link X2 X1;

link X4 D2;

link X4 X6;

link X5 D2;

link X5 X4;

link X6 D2;

link X6 X1;

link X7 D1;

link X7 X5;

link X8 D1;

link X8 X7;

link X9 D1;

link X9 X7;

//Network Relationships: 

relation X7 X9 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 0.5918378042951339 0.5923436228257695 0.0 0.6467272072797526 0.0 0.0 0.4081621957048661 0.40765637717423053 1.0 0.35327279272024736 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8564380602860829 0.12838548588349763 0.015176453830419382 );
}

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 );
}

relation X4 X5 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5417978559950773 0.3569037729207757 0.8293745210722558 0.0 0.5491418447641391 0.0 0.89016140442277 0.6815615719778966 0.0 0.7103652456224825 1.0 1.0 0.45820214400492265 0.6430962270792244 0.17062547892774413 1.0 0.45085815523586104 1.0 0.10983859557722997 0.31843842802210337 0.0 0.28963475437751757 0.0 0.0 );
}

relation X5 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.481945610671421 0.0 0.163611076892198 0.8870787080839687 0.35444331243638116 0.11292129191603137 );
}

relation X6 X4 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 1.0 0.39294812181350425 0.7824562492299663 0.2678356570262219 0.0 1.0 1.0 0.0 0.0 0.6070518781864958 0.21754375077003368 0.7321643429737781 0.0 0.0 );
}

relation X1 X6 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.18128844818896106 0.5229263103820702 0.11500331556787446 0.13557380983837686 0.14203897767652732 0.10553924646307868 0.7743751476629626 0.8495701362713798 0.3637582284741708 0.24016396877050294 0.0 0.01485605389024336 0.3129143456603408 0.13137047438434826 0.11062153676916298 0.0 );
}

relation X2 D2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.5024527991573522 1.0 0.4741243080867784 0.3062116780673427 1.0 0.14884791110789322 0.0 0.0 0.0 0.0 0.3036307742059158 0.32336095466594633 0.0 0.6612216470448024 0.0 0.0 0.4975472008426479 0.0 0.5258756919132217 0.6937883219326574 0.0 0.8511520888921067 1.0 0.0 1.0 1.0 0.6963692257940842 0.6766390453340537 1.0 0.33877835295519765 0.0 );
}

relation X3 X1 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4940046278143841 0.0 0.09128108395611939 0.6937608342158121 0.9473523401278698 0.0 0.9266420057977311 0.03625277560657421 0.0 0.0 0.9554356656917672 7.7549611852979E-4 0.0 0.21552365344032368 0.8016836014645043 0.13288154446908212 0.505995372185616 0.017084591135800607 0.9087189160438806 0.30623916578418786 0.052647659872130215 1.0 0.0 0.010809280431710078 1.0 0.8269775204255123 0.0 0.3645979949012872 0.7802715256831918 0.0 0.06019754717396358 0.8614282507620469 0.0 0.9829154088641995 0.0 0.0 0.0 0.0 0.07335799420226881 0.9529379439617157 0.0 0.17302247957448771 0.04456433430823284 0.6346265089801831 0.2197284743168082 0.7844763465596762 0.13811885136153199 0.0056902047688710215 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (114.18244844868737 0.0 106.88630221221204 103.02800877104355 0.0 51.93793838154894 0.0 27.037550425491666 79.11826538318982 92.86421366160329 71.8221191467145 67.963825705546 0.0 126.23591791269133 105.19382339780256 101.33552995663406 );
}

}
