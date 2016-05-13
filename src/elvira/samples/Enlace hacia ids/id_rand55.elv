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
num-states = 2;
states = (x8_1 x8_2);
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
num-states = 3;
states = (d1_1 d1_2 d1_3);
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
num-states = 2;
states = (d2_1 d2_2);
}

node X1(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x1_1 x1_2 x1_3);
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
num-states = 2;
states = (x3_1 x3_2);
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

link X2 X1;

link X3 X2;

link X4 D2;

link X4 V0;

link X4 X6;

link X5 D2;

link X5 X4;

link X6 D2;

link X6 X1;

link X7 D1;

link X7 X4;

link X8 D1;

link X8 X7;

link X9 D1;

link X9 X7;

//Network Relationships: 

relation X7 X9 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 0.27366819394443753 0.5564835157010392 0.0 0.0 0.7263318060555625 0.44351648429896084 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.19408128281109693 0.8059187171889031 );
}

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.13375600903714535 0.8662439909628546 );
}

relation X4 X5 X7 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.1486332456116962 0.9707956989598963 0.0 0.14932701152346972 1.0 1.0 0.0 0.16427929201765332 0.505580283414786 0.18276381182370502 0.6349253795956243 0.019180917606860522 0.6000843079806258 0.9907660717004636 1.0 0.05286096050909207 0.6929211880534522 1.0 0.8513667543883039 0.029204301040103707 0.0 0.8506729884765302 0.0 0.0 1.0 0.8357207079823468 0.4944197165852139 0.8172361881762951 0.36507462040437566 0.9808190823931395 0.39991569201937416 0.00923392829953644 0.0 0.9471390394909079 0.3070788119465478 0.0 );
}

relation X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.11916360610930275 0.8378127419249252 0.043023651965772056 );
}

relation X6 X4 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5174901973528782 0.0 1.0 0.0 0.2735892324483908 0.0 0.4825098026471218 1.0 0.0 1.0 0.726410767551609 1.0 );
}

relation X1 X6 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.1950558839065614 0.8137880396722537 0.3144476610011327 0.9459919902840809 0.1251319210473674 0.18621196032774634 0.5568768693768976 0.05400800971591907 0.6798121950460712 0.0 0.12867546962196982 );
}

relation X2 D2 X3 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 0.0 0.7608688193374348 0.36411442141364875 0.6387998734175416 0.4985620800243426 0.0 0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.2391311806625653 0.6358855785863512 0.36120012658245837 0.5014379199756573 0.0 1.0 1.0 1.0 0.0 );
}

relation X3 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8442250224725043 0.06739112753430998 0.0 0.1557749775274957 0.93260887246569 0.0 );
}

relation V0 D1 D2 X4 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (0.0 0.0 197.83498015758352 0.0 199.39914666710382 206.97520699223898 0.0 211.00634265819028 163.98423869476682 0.0 168.0153743607181 175.59143468585324 );
}

}
