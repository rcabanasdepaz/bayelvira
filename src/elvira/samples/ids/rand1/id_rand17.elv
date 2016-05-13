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
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x10_1 x10_2 x10_3);
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
pos_x =1;
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

link D1 X4;

link D2 V0;

link D2 X1;

link D2 X2;

link D2 X3;

link X10 D1;

link X10 X5;

link X3 X1;

link X4 D2;

link X4 X6;

link X5 D2;

link X5 V0;

link X5 X4;

link X5 X6;

link X6 D2;

link X6 V0;

link X7 D1;

link X7 X8;

link X7 X9;

link X8 D1;

link X8 X5;

link X9 D1;

link X9 X10;

//Network Relationships: 

relation X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4355145019855404 0.5644854980144595 );
}

relation X8 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7421550337735834 0.5068148469723108 0.2578449662264166 0.49318515302768917 );
}

relation X9 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.19320229120983234 0.47323071032936836 0.8067977087901675 0.5267692896706316 );
}

relation X10 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4645123539366384 0.22162205414285124 0.020320066297469394 0.5022968604451652 0.5151675797658921 0.27608108541198356 );
}

relation X4 D1 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5762655194346937 0.22945554908314622 0.27476271917506945 0.6967579594470911 0.3136073624711318 0.47327425954718855 0.26986562789080937 0.37664383997649925 0.6056896982653708 0.4237344805653064 0.7705444509168538 0.7252372808249306 0.3032420405529088 0.6863926375288683 0.5267257404528114 0.7301343721091907 0.6233561600235007 0.39431030173462916 );
}

relation X5 X10 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.20065866643155583 0.3160458738045605 0.21844639240778993 0.18250809239969096 0.201561876533366 0.1480374939497332 0.35390715891419255 0.07836882073444021 0.3799751035756258 0.43486983516432653 0.3419298688801552 0.4034704152600191 0.44543417465425167 0.6055853054609992 0.40157850401658424 0.38262207243598245 0.4565082545864788 0.44849209079024777 );
}

relation X6 X4 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.48337248456577847 0.16910070522266568 0.425601762319937 0.015312213907370307 0.13143248763986864 0.5167313753859709 0.5166275154342216 0.8308992947773343 0.574398237680063 0.9846877860926297 0.8685675123601314 0.4832686246140291 );
}

relation X1 D1 D2 X3 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.29123509752048476 0.7111626732513061 0.4603240434895745 0.3082396149160749 0.30788104178234094 0.28928292046019516 0.4200097901447619 0.30836384305318193 0.8497284603015756 0.43462716669799617 0.3362265315436993 0.5076404996790659 0.3409508566492034 0.08918846896479478 0.1897018573528706 0.23497001754834168 0.5348170907159232 0.2594338243482973 0.18336833117766 0.31878087402236127 0.04627205257997001 0.37049780221271744 0.07201169273377758 0.015567526381787246 0.36781404583031174 0.19964885778389901 0.34997409915755484 0.4567903675355834 0.15730186750173586 0.4512832551915074 0.3966218786775781 0.37285528292445674 0.10399948711845435 0.19487503108928644 0.591761775722523 0.47679197393914674 );
}

relation X2 D1 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5843883496561738 0.8689760572186233 0.4553956973429805 0.29819387725338986 0.47815493871179615 0.5535427084805801 0.41561165034382613 0.13102394278137683 0.5446043026570194 0.7018061227466101 0.5218450612882038 0.4464572915194199 );
}

relation X3 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.31545558698520193 0.7663219121127337 0.6845444130147981 0.23367808788726635 );
}

relation V0 D1 D2 X5 X6 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (225.5549821555233 132.45902071411967 239.85824683417783 146.7622853927742 266.89891115994044 173.8029497185368 213.32689190342563 120.230930462022 227.63015658208016 134.53419514067653 254.6708209078428 161.57485946643916 256.03253721836995 162.9365757769663 270.33580189702445 177.23984045562082 297.3764662227871 204.28050478138346 243.80444696627225 150.70848552486862 258.1077116449268 165.01175020352315 285.1483759706894 192.05241452928578 246.65561210711408 153.55965066571045 260.9588767857686 167.86291534436498 287.99954111153124 194.9035796701276 234.4275218550164 141.33156041361278 248.73078653367094 155.6348250922673 275.77145085943357 182.67548941802994 );
}

}
