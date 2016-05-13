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

relation X7 X8 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9794906735663867 0.5771788049750205 0.8384643798149445 0.6224001654508082 0.02050932643361327 0.4228211950249795 0.1615356201850556 0.3775998345491917 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.23141430542636898 0.768585694573631 );
}

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.17688659463052595 0.8231134053694741 );
}

relation X4 D1 X5 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5944705949267648 0.3611236241031685 0.07690144336418646 0.15501055943125913 0.6829242625433 0.8254257534419321 0.569708269174668 0.23932360038592468 0.451836624697578 0.5634320961449107 0.2253883612140298 0.03120190605512666 0.462405680928492 0.33959939279656404 0.09861761235609316 0.2449770944490185 0.35460886665557995 0.4162496140251816 0.4055294050732353 0.6388763758968315 0.9230985566358135 0.8449894405687409 0.31707573745670004 0.17457424655806786 0.430291730825332 0.7606763996140754 0.5481633753024219 0.4365679038550892 0.7746116387859703 0.9687980939448734 0.5375943190715081 0.6604006072034359 0.9013823876439069 0.7550229055509814 0.6453911333444201 0.5837503859748183 );
}

relation X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.2950931910941433 0.25732561506521207 0.44758119384064454 );
}

relation X6 D1 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.16668434577390914 0.24029800257058212 0.49057440884797926 0.640717608630336 0.6005426054735642 0.7820523798275043 0.8333156542260909 0.7597019974294178 0.5094255911520208 0.3592823913696641 0.3994573945264357 0.2179476201724957 );
}

relation X1 X2 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.15711959909359885 0.1033940944918516 0.08931869004832693 0.4442210783788248 0.6893744415733543 0.4406479947898094 0.8144730139546555 0.09802479442092771 0.15350595933304695 0.45595791071833897 0.09620829599701755 0.4577541272002475 );
}

relation X2 D1 D2 X3 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4406936987839108 0.4453428390277756 0.4046243123921014 0.8877413733021138 0.4081338209158394 0.4234417371352284 0.4235871081247276 3.1036370528104014E-4 0.4727454147582817 0.4199773506162489 0.24725700819328117 0.17069877182728918 0.5593063012160892 0.5546571609722243 0.5953756876078986 0.11225862669788618 0.5918661790841606 0.5765582628647716 0.5764128918752724 0.9996896362947189 0.5272545852417184 0.580022649383751 0.7527429918067189 0.8293012281727109 );
}

relation X3 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7642102418608658 0.24790598649616313 0.17694345031715422 0.23578975813913416 0.7520940135038368 0.8230565496828458 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (110.48154653535961 98.25345628326195 140.95910159820625 128.73101134610857 131.5821764869504 119.35408623485273 );
}

}
