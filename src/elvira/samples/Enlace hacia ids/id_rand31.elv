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
values= table (0.3929638039036903 0.5695340379773585 0.9585830813144036 0.0 0.6070361960963098 0.4304659620226416 0.04141691868559641 0.0 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8848479561463939 0.11515204385360606 );
}

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7733053387811382 0.22669466121886198 );
}

relation X4 X5 X7 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6017551853968302 0.0 0.40156155346670075 0.7613954431196966 1.0 1.0 0.4542986698895005 0.4525273920557112 0.0 0.8277636721690164 0.30126085621014775 0.0 0.7502082275996113 0.0 0.22951155059768888 0.7422940960736992 0.24462588907986876 0.9733797661944299 0.39824481460316996 1.0 0.5984384465332993 0.2386045568803034 0.0 0.0 0.5457013301104994 0.5474726079442889 1.0 0.1722363278309837 0.6987391437898524 1.0 0.24979177240038875 1.0 0.7704884494023112 0.2577059039263007 0.7553741109201312 0.026620233805570027 );
}

relation X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.08402276255625975 0.6001696102980573 0.315807627145683 );
}

relation X6 X4 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 0.5870129895297487 1.0 0.0 0.0 0.0 1.0 0.41298701047025127 0.0 0.0 0.0 );
}

relation X1 X6 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6805162659640654 0.010357273199810705 1.0 0.4366159656697539 0.3194837340359345 0.2426457015279215 0.0 0.4009098922083249 0.0 0.7469970252722677 0.0 0.1624741421219213 );
}

relation X2 D2 X3 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.4171637718280574 0.5992232900914607 0.460133043416402 0.0 1.0 0.41272247495857933 0.9972771092650267 0.0 1.0 0.7656903713271455 0.5614619476899969 1.0 0.5828362281719426 0.40077670990853936 0.5398669565835981 1.0 0.0 0.5872775250414207 0.0027228907349733174 1.0 0.0 0.23430962867285451 0.43853805231000303 );
}

relation X3 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.2019275412178354 0.5456050478542612 0.9894941353421102 0.7980724587821646 0.4543949521457388 0.010505864657889826 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (74.93129652126622 0.0 24.922987243815832 66.07641758936221 28.753123060004004 69.90655340555037 );
}

}
