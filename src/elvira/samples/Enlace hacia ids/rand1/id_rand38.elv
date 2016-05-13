// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
}

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x11_1 x11_2);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x12_1 x12_2 x12_3);
}

node X13(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x13_1 x13_2 x13_3);
}

node X14(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x14_1 x14_2);
}

node X15(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x15_1 x15_2);
}

node X16(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x16_1 x16_2);
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
pos_x =4;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x5_1 x5_2);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
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

link D1 X3;

link D1 X4;

link D1 X5;

link D1 X7;

link D1 X8;

link D1 X9;

link D2 V0;

link D2 X1;

link X10 D1;

link X10 X11;

link X10 X3;

link X11 D1;

link X11 X12;

link X12 D1;

link X12 X13;

link X13 D1;

link X13 X3;

link X14 D1;

link X14 X10;

link X15 D1;

link X15 X10;

link X16 X10;

link X2 X5;

link X3 X4;

link X4 X1;

link X5 D2;

link X5 X7;

link X5 X8;

link X6 D2;

link X6 X12;

link X6 X13;

link X7 D2;

link X8 D2;

link X9 D2;

link X9 X7;

link X9 X8;

//Network Relationships: 

relation X10 X14 X15 X16 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6127620883053755 0.7920185857600648 0.5906784608938495 0.6319781209203447 0.49871924245442806 0.5183396236822136 0.37082519360033606 0.6904546822662694 0.38723791169462446 0.20798141423993527 0.4093215391061506 0.3680218790796554 0.5012807575455719 0.4816603763177863 0.6291748063996639 0.3095453177337306 );
}

relation X11 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3902939486021079 0.6873576388399809 0.6097060513978921 0.3126423611600192 );
}

relation X12 X11 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7117487670621749 0.44436226470921036 0.46196640678311424 0.4318416413092409 0.1339349667326027 0.11782638620703569 0.13488456229300363 0.4594087589497638 0.1543162662052225 0.43781134908375385 0.40314903092388216 0.1087495997409952 );
}

relation X13 X12 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5752136778989596 0.2018212783701753 0.5054812361824499 0.2771178367087336 0.51135351866302 0.47112405008121305 0.1524134743098961 0.5071666390780926 0.3926507333136801 0.504162714046509 0.1157043655721098 0.47433747624805345 0.2723728477911443 0.291012082551732 0.10186803050386994 0.21871944924475734 0.37294211576487013 0.05453847367073353 );
}

relation X14 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.43922946688320114 0.5607705331167988 );
}

relation X15 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4161294329067514 0.5838705670932486 );
}

relation X16 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.14995093478260357 0.8500490652173964 );
}

relation X5 D1 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6918619927772764 0.29311276949807424 0.2977249994345491 0.5499863391456998 0.3135667685021231 0.36137049422438416 0.3081380072227235 0.7068872305019258 0.7022750005654508 0.4500136608543002 0.6864332314978769 0.6386295057756158 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9428112407162297 0.05718875928377036 );
}

relation X7 D1 X5 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8525030485007036 0.49909996681892715 0.07982937388598159 0.5025064776732051 0.23874381706911033 0.6016551295978261 0.903369101608826 0.5074264575008256 0.8821479232468097 0.6004935057690591 0.6082758884513066 0.6036345997483493 0.14749695149929637 0.5009000331810729 0.9201706261140185 0.49749352232679495 0.7612561829308896 0.39834487040217387 0.09663089839117397 0.4925735424991745 0.11785207675319033 0.39950649423094076 0.39172411154869347 0.39636540025165073 );
}

relation X8 D1 X5 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.664301904007508 0.8011049295108412 0.4287244040745427 0.3420052947548708 0.4841859377511573 0.4257247921249403 0.4972194162702883 0.22947102664381194 0.8686065084785284 0.47789118093148175 0.88087036977915 0.5869184836778594 0.33569809599249195 0.19889507048915878 0.5712755959254573 0.6579947052451293 0.5158140622488427 0.5742752078750598 0.5027805837297117 0.7705289733561881 0.13139349152147156 0.5221088190685184 0.11912963022085 0.4130815163221406 );
}

relation X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.2032277243360827 0.07946685439120835 0.39769676182808816 0.7967722756639173 0.9205331456087917 0.6023032381719118 );
}

relation X1 D1 D2 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6903652533517665 0.0471068738404444 0.46285794505995514 0.021524926098213267 0.5108152970586365 0.03489887609311935 0.4747868201714336 0.5392059098756709 0.25984781220194214 0.4693761635582455 0.20601352938938275 0.3636797636844156 0.34348789934829455 0.44271695405224837 0.21842244504014022 0.7187727639851941 0.3230555446982343 0.21389126090666802 0.03798368383400531 0.3007884411058382 0.11605248797758103 0.29714940018670005 0.22381344352185828 0.6110817388402374 0.039119336300782716 0.14045694635485245 0.26684960197596463 0.07728170404253616 0.1257634139609356 0.256014897148204 0.636364890327787 0.0890650488564881 0.2835997574154358 0.16176639228234513 0.11050336814031353 0.22092544506578043 0.2716510628142283 0.6521046850537173 0.4210895669624639 0.6813256737150868 0.2653712594195051 0.3540193850666433 0.4860938435277837 0.32033714376947664 0.47330258582209317 0.4533421323992183 0.6682230566496816 0.38030533916738035 0.020147210323918398 0.46821799709126355 0.49797779754442384 0.11946084373246076 0.566441087161452 0.5651832940275515 );
}

relation X2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.21940196840786028 0.0636255950707983 0.7058823424772004 0.7805980315921396 0.9363744049292017 0.29411765752279967 );
}

relation X3 D1 X10 X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8203671978251343 0.33107799906444174 0.0576282624088001 0.4446514257426188 0.555683820294008 0.7631573969668695 0.3549582836309736 0.0871640243114852 0.6531704231295229 0.4233010525451826 0.6130661677474931 0.8637386039113993 0.09955501905071912 0.32068850433350543 0.48595951795861403 0.18844871895284293 0.16888314335500135 0.18142801115628598 0.17963280217486577 0.6689220009355582 0.9423717375912 0.5553485742573813 0.44431617970599213 0.2368426030331305 0.6450417163690264 0.9128359756885147 0.34682957687047716 0.5766989474548173 0.3869338322525068 0.13626139608860063 0.9004449809492809 0.6793114956664945 0.514040482041386 0.811551281047157 0.8311168566449987 0.818571988843714 );
}

relation X4 D1 X3 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.027317644113503414 0.21918485775403285 0.4756608219930965 0.14709772526942932 0.3421807609182996 0.3298695214630794 0.5690114560351626 0.22792390853420696 0.3449137427624271 0.46684527861336317 0.4411452032865954 0.5953044997590498 0.403670899851334 0.5528912337117602 0.17942543524447646 0.38605699611720756 0.216674035795105 0.0748259787778708 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (110.48154653535961 98.25345628326195 140.95910159820625 128.73101134610857 131.5821764869504 119.35408623485273 );
}

}
