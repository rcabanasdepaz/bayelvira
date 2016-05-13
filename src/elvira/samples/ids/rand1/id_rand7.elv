// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x9_1 x9_2 x9_3);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
}

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x11_1 x11_2 x11_3);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x12_1 x12_2);
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
pos_x =3;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
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
pos_x =6;
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
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x3_1 x3_2);
}

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x4_1 x4_2 x4_3);
}

node V0(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_x =4;
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

link D1 X2;

link D1 X7;

link D1 X8;

link D2 V0;

link D2 X3;

link X1 X5;

link X10 D1;

link X10 X4;

link X11 D1;

link X11 X12;

link X11 X9;

link X12 D1;

link X12 X9;

link X2 X5;

link X4 X2;

link X5 D2;

link X5 X7;

link X6 D2;

link X6 X11;

link X6 X12;

link X7 D2;

link X8 D2;

link X8 X5;

link X8 X7;

link X9 D1;

link X9 X10;

//Network Relationships: 

relation X9 X11 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4346586117135504 0.26082823025119845 0.28875831506734195 0.3817262666259689 0.07603221519027717 0.3422987585889573 0.10323907198079738 0.4630731520460382 0.26834336373805695 0.4694736591905026 0.36485334318099594 0.3503589742098076 0.46210231630565235 0.27609861770276345 0.4428983211946011 0.14880007418352856 0.5591144416287268 0.30734226720123503 );
}

relation X10 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8881518842409617 0.03878103231523429 0.7989081014224002 0.1118481157590384 0.9612189676847658 0.20109189857759974 );
}

relation X11 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.05682629779008959 0.1058391911760111 0.4275428882531342 0.47962575658333995 0.5156308139567762 0.41453505224064907 );
}

relation X12 X11 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4947733946390276 0.7292376357270252 0.6847301061228656 0.5223857571743443 0.4966057445428499 0.5934396965745737 0.5052266053609725 0.2707623642729749 0.3152698938771345 0.4776142428256558 0.5033942554571501 0.4065603034254262 );
}

relation X5 X1 X2 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.27409377974862686 0.320046458683628 0.44233497904163294 0.6716413038521909 0.11351793475412328 0.9586806380117092 0.3569771664591646 0.9095694792184724 0.46775050855184735 0.0833039685840778 0.3906342612785778 0.7349548188453231 0.7259062202513731 0.679953541316372 0.5576650209583671 0.3283586961478092 0.8864820652458767 0.041319361988290754 0.6430228335408354 0.09043052078152745 0.5322494914481526 0.9166960314159222 0.6093657387214221 0.2650451811546768 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5452913364800551 0.45470866351994493 );
}

relation X7 D1 X5 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.2869810624325133 0.47937165469710996 0.049076373474904934 0.6300506006586167 0.4256040360970701 0.9252073059737073 0.6339500019381058 0.5864903607537592 0.41983608585897275 0.43431861415985745 0.7098684245387272 0.636385739449468 0.7130189375674868 0.5206283453028899 0.950923626525095 0.36994939934138327 0.5743959639029299 0.07479269402629275 0.36604999806189414 0.4135096392462408 0.5801639141410272 0.5656813858401426 0.29013157546127283 0.3636142605505319 );
}

relation X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7646704482307912 0.5334781248276298 0.4850063020240347 0.23532955176920875 0.4665218751723701 0.5149936979759653 );
}

relation X1 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4004173284430195 0.17712615932490153 0.09403354510537362 0.0013154872684013551 0.3812539526829215 0.47029381750586585 0.5982671842885792 0.44161988799217694 0.43567263738876055 );
}

relation X2 D1 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5364108305314451 0.2729744302615461 0.5932536530931051 0.6794385676694731 0.44506973914754466 0.45524730485005566 0.4833319059752631 0.43848249936913775 0.2980578918990036 0.4635891694685549 0.7270255697384539 0.4067463469068949 0.3205614323305269 0.5549302608524553 0.5447526951499444 0.5166680940247369 0.5615175006308624 0.7019421081009963 );
}

relation X3 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5796080047067497 0.8462826051087936 0.4203919952932504 0.15371739489120648 );
}

relation X4 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.01192301309778804 0.5299291604986011 0.5592695485376432 0.29609151745305196 0.42880743836456875 0.17397932204834687 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (110.48154653535961 98.25345628326195 140.95910159820625 128.73101134610857 131.5821764869504 119.35408623485273 );
}

}
