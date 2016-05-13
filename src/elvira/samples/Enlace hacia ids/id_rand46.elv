// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x8_1 x8_2 x8_3);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
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
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x11_1 x11_2 x11_3);
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
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x4_1 x4_2);
}

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x5_1 x5_2 x5_3);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
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
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
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

link D1 X1;

link D1 X3;

link D1 X4;

link D1 X6;

link D2 V0;

link D2 X3;

link X1 X3;

link X10 D1;

link X10 X11;

link X11 D1;

link X11 X8;

link X2 X1;

link X4 D2;

link X4 V0;

link X5 D2;

link X5 X10;

link X5 X7;

link X6 D2;

link X6 X2;

link X6 X4;

link X7 D2;

link X7 X8;

link X8 D1;

link X8 X4;

link X9 D1;

link X9 X4;

//Network Relationships: 

relation X8 X7 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.42857085635790126 0.7204141445603698 0.402564175287942 0.7810444439683197 0.3488101921758772 0.23861569123456655 0.47388298901659237 0.2795858554396302 0.5146093244251781 0.2189555560316803 0.0 0.010794476402217416 0.0975461546255062 0.0 0.08282650028687984 0.0 0.6511898078241228 0.7505898323632161 );
}

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9035886131967528 0.09641138680324715 );
}

relation X10 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 0.0 0.0 1.0 0.0 );
}

relation X11 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8403037243422776 0.04718679144024568 0.00435479123491399 0.9528132085597543 0.1553414844228084 0.0 );
}

relation X4 X6 X8 X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.24208356332385342 0.7574999838943682 1.0 1.0 1.0 0.19993775474912456 0.5698389347127042 1.0 1.0 0.03324135583058845 0.3920551979229485 0.8053021433451496 0.5017087159202828 1.0 0.4488308903233784 0.0 1.0 0.2724987323248335 0.6022501350734195 0.8984794625799745 0.0 0.0 0.0 0.0 0.43443230607435424 0.41012097010079435 0.422510581490434 1.0 0.3202672769455351 1.0 0.0 0.0 0.5405848319189267 0.0 0.235662231875223 0.9319451842580343 0.7579164366761467 0.24250001610563177 0.0 0.0 0.0 0.8000622452508754 0.43016106528729575 0.0 0.0 0.9667586441694115 0.6079448020770516 0.19469785665485034 0.4982912840797173 0.0 0.5511691096766216 0.0 0.0 0.7275012676751664 0.39774986492658054 0.10152053742002562 1.0 1.0 1.0 1.0 0.5655676939256459 0.5898790298992057 0.577489418509566 0.0 0.6797327230544649 0.0 1.0 1.0 0.45941516808107336 1.0 0.764337768124777 0.06805481574196572 );
}

relation X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9355880841033708 0.0 0.06441191589662926 );
}

relation X6 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8745363890347319 0.0 0.9640067613302724 0.12546361096526812 0.0 0.035993238669727576 );
}

relation X7 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 1.0 0.7487391908456796 0.0 0.0 0.2512608091543203 );
}

relation X1 X2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.2924925731197079 0.0 0.0 0.8356947004553208 0.0 0.8341751018177924 0.0 1.0 0.0 0.16430529954467923 0.0 0.16582489818220753 0.7075074268802921 0.0 1.0 0.0 1.0 0.0 );
}

relation X2 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.12471657537693845 0.8431801382331631 0.8752834246230615 0.15681986176683693 );
}

relation X3 D2 X1 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4925319681046325 0.9953732459130474 0.0 0.0 0.4921180183792287 0.5660792084344308 0.6010838535141935 0.29215698922993144 1.0 0.9982213232093297 1.0 0.0 1.0 0.4944685114371595 0.5062470123809563 0.0 0.21964840243525138 0.5130143283497086 0.5074680318953676 0.004626754086952586 1.0 1.0 0.5078819816207714 0.4339207915655692 0.3989161464858066 0.7078430107700687 0.0 0.0017786767906704172 0.0 1.0 0.0 0.5055314885628405 0.49375298761904385 1.0 0.7803515975647486 0.48698567165029144 );
}

relation V0 D1 D2 X4 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (77.22424913260528 0.0 78.1419865012458 76.98031433078249 103.02269215217169 0.0 103.9404295208122 102.7787573503489 159.00617971882664 0.0 0.0 0.0 );
}

}
