// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x11_1 x11_2);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x12_1 x12_2);
}

node X13(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x13_1 x13_2);
}

node X14(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x14_1 x14_2);
}

node D1(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =2;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (d1_1 d1_2);
}

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x5_1 x5_2);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
}

node D2(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =5;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (d2_1 d2_2);
}

node X1(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x1_1 x1_2);
}

node X2(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x2_1 x2_2);
}

node X3(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x3_1 x3_2);
}

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x4_1 x4_2);
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

link D1 X1;

link D1 X10;

link D1 X3;

link D1 X7;

link D1 X8;

link D2 V0;

link D2 X3;

link D2 X4;

link X1 X7;

link X10 X5;

link X11 D1;

link X11 X13;

link X12 D1;

link X12 X11;

link X13 D1;

link X13 X14;

link X14 D1;

link X14 X5;

link X2 X13;

link X2 X3;

link X2 X4;

link X5 D2;

link X5 X7;

link X5 X9;

link X6 D2;

link X6 X11;

link X7 D2;

link X7 X9;

link X8 D2;

link X8 X1;

link X8 X5;

link X9 D2;

//Network Relationships: 

relation X11 X12 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9054051752235511 0.3476649582956469 0.72600012314599 0.4408398578012595 0.09459482477644889 0.6523350417043531 0.2739998768540099 0.5591601421987404 );
}

relation X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5674101469349839 0.432589853065016 );
}

relation X13 X11 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8568453254368286 0.36770123281324746 0.4495013071056585 0.5372085257362442 0.1431546745631714 0.6322987671867526 0.5504986928943416 0.46279147426375583 );
}

relation X14 X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5015094910503273 0.22211994671796173 0.4984905089496728 0.7778800532820384 );
}

relation X5 X10 X14 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.42649933370071164 0.6065487331425513 0.1287532483249292 0.5871961162357499 0.006964368093370637 0.8237015152027957 0.48718824764902313 0.5007637674857509 0.5735006662992884 0.3934512668574486 0.8712467516750707 0.4128038837642501 0.9930356319066295 0.17629848479720442 0.5128117523509768 0.4992362325142492 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.27534073176070845 0.7246592682392916 );
}

relation X7 D1 X1 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.23710922108546031 0.07532868016722942 0.17243257120958158 0.5061366047775746 0.3499299975298755 0.6738206992316197 0.7915580136213941 0.5207886627039839 0.7628907789145396 0.9246713198327706 0.8275674287904184 0.49386339522242545 0.6500700024701245 0.3261793007683803 0.2084419863786058 0.4792113372960161 );
}

relation X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6630643662900754 0.7906455347770291 0.33693563370992463 0.20935446522297085 );
}

relation X9 X5 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6838474790177952 0.6468225595582131 0.434474751057597 0.3502834257304189 0.31615252098220475 0.35317744044178684 0.5655252489424031 0.6497165742695813 );
}

relation X10 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.688387846810676 0.314490670487868 0.311612153189324 0.685509329512132 );
}

relation X1 D1 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.27873801718187374 0.6055585301496571 0.35533899382690376 0.8750142103887839 0.7212619828181263 0.3944414698503428 0.6446610061730963 0.1249857896112161 );
}

relation X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3338887137306007 0.6661112862693994 );
}

relation X3 D1 D2 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.2619372783317403 0.6183447707344731 0.3126234790740226 0.18893774893511178 0.48708638377159325 0.5681576287077866 0.8502971463789034 0.501792429571728 0.7380627216682597 0.3816552292655269 0.6873765209259775 0.8110622510648883 0.5129136162284068 0.43184237129221337 0.1497028536210965 0.49820757042827196 );
}

relation X4 D2 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7264667096277839 0.2928599096662653 0.6252420602391833 0.4603889461275615 0.2735332903722161 0.7071400903337347 0.37475793976081667 0.5396110538724385 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (150.41706200977592 110.48154653535961 180.89461707262254 140.95910159820625 );
}

}
