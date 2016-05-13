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

relation X7 X8 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9855927886864251 0.6237195540484858 0.5109599075649652 0.36506294270490525 0.014407211313574923 0.3762804459515141 0.48904009243503466 0.6349370572950948 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7842536799156172 0.2157463200843828 );
}

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0427054758647758 0.9572945241352242 );
}

relation X4 D1 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3138759761495526 0.49663011295692905 0.43095928882266515 0.50359029848054 0.265960038802428 0.9837359263438987 0.3096897047028597 0.508817546581211 0.13543317564006335 0.6861240238504475 0.5033698870430708 0.5690407111773348 0.49640970151945996 0.734039961197572 0.016264073656101278 0.6903102952971403 0.4911824534187889 0.8645668243599367 );
}

relation X5 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4118863611943351 0.3875950354644264 0.4050341152399638 0.2071015562814745 0.18307952356570117 0.40530340825409905 );
}

relation X6 D1 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6663738070792893 0.8557693318744255 0.6642603899741425 0.5898001014608704 0.29187626404517725 0.539038497042042 0.3336261929207108 0.1442306681255745 0.3357396100258574 0.41019989853912964 0.7081237359548227 0.460961502957958 );
}

relation X1 X2 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.05464509791515254 0.005100718230993238 0.11975001747055096 0.5542151305618089 0.6429966712976936 0.3856853157507633 0.057194913575030426 0.08413929303646127 0.30235823078715385 0.6092139660182435 0.8230550689544186 0.36164557640172973 );
}

relation X2 D1 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.484242815481044 0.5161364165370941 0.20496893286008977 0.1103052790892404 0.609090369216934 0.2835117367570133 0.515757184518956 0.483863583462906 0.7950310671399103 0.8896947209107595 0.3909096307830659 0.7164882632429866 );
}

relation X3 D1 X1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.333653349401452 0.7352893910832545 0.448126257991602 0.5333657912841342 0.3108160552558941 0.7979120716691769 0.220220913309785 0.9736569978927496 0.15871666841801554 0.666346650598548 0.2647106089167456 0.551873742008398 0.4666342087158658 0.6891839447441058 0.20208792833082323 0.7797790866902149 0.02634300210725032 0.8412833315819844 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (110.48154653535961 98.25345628326195 140.95910159820625 128.73101134610857 131.5821764869504 119.35408623485273 );
}

}
