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
values= table (0.3369293773772141 0.6630706226227857 );
}

relation X8 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.706922406331291 0.766453039910755 0.2930775936687089 0.2335469600892451 );
}

relation X9 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.644935792000879 0.5894721057066957 0.3550642079991209 0.4105278942933042 );
}

relation X10 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.17709713002199698 0.3481754862579676 0.4021565671529881 0.05032307922727267 0.420746302825015 0.6015014345147597 );
}

relation X4 D1 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.36545832137700957 0.7832731447468356 0.32357963247261895 0.6091987278736175 0.6031420407162732 0.43365596582600374 0.6256958867279335 0.157160504328302 0.7820461734070873 0.6345416786229904 0.21672685525316449 0.6764203675273811 0.39080127212638255 0.3968579592837269 0.5663440341739963 0.3743041132720665 0.8428394956716979 0.21795382659291268 );
}

relation X5 X10 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5210619400014116 0.16351662625212832 0.37544326541549755 0.4695974865035188 0.4010782184441658 0.05131769491334429 0.19626350297297848 0.3098145395718132 0.33430812215701455 0.08185834112515787 0.32896300984885163 0.7423894652429902 0.28267455702561 0.5266688341760585 0.29024861242748795 0.4485441723713234 0.26995877170698257 0.20629283984366553 );
}

relation X6 X4 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4060205635827155 0.45279023890316467 0.6163730366743758 0.1798207862647783 0.5289801628846094 0.26983093368833944 0.5939794364172846 0.5472097610968354 0.3836269633256242 0.8201792137352217 0.47101983711539064 0.7301690663116606 );
}

relation X1 D1 D2 X3 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.28887799788009483 0.6433506351054515 0.06027333095699832 0.1977574499536167 0.3745325624870437 0.1708529343667257 0.19403025990849346 0.7480801477221901 0.43165924690763474 0.21825272058323397 0.656313301913904 0.88805513429515 0.47157189896114954 0.04852189034809247 0.677276800610781 0.5866694018227334 0.5937600588317974 5.20492050975353E-4 0.46491593082711535 0.2290461603599993 0.11959949583906065 0.528407896058919 0.014854023870990125 0.02959718379887626 0.2395501031587557 0.308127474546456 0.2624498684322206 0.21557314822364995 0.031707378681159054 0.828626573582299 0.34105380926439105 0.02287369191781055 0.4487412572533046 0.253339383357847 0.3288326742151058 0.08234768190597376 );
}

relation X2 D1 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6021828124814707 0.901357613035943 0.556273552648946 0.8430401012931654 0.2508914548795959 0.5227500968956856 0.39781718751852935 0.098642386964057 0.44372644735105404 0.1569598987068347 0.7491085451204041 0.47724990310431437 );
}

relation X3 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4247458553797042 0.3235034171826331 0.5752541446202957 0.6764965828173668 );
}

relation V0 D1 D2 X5 X6 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (225.5549821555233 132.45902071411967 239.85824683417783 146.7622853927742 266.89891115994044 173.8029497185368 213.32689190342563 120.230930462022 227.63015658208016 134.53419514067653 254.6708209078428 161.57485946643916 256.03253721836995 162.9365757769663 270.33580189702445 177.23984045562082 297.3764662227871 204.28050478138346 243.80444696627225 150.70848552486862 258.1077116449268 165.01175020352315 285.1483759706894 192.05241452928578 246.65561210711408 153.55965066571045 260.9588767857686 167.86291534436498 287.99954111153124 194.9035796701276 234.4275218550164 141.33156041361278 248.73078653367094 155.6348250922673 275.77145085943357 182.67548941802994 );
}

}
