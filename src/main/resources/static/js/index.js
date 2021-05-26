var inicio = new Vue({
	el:"#inicio",
    data: {
        funcionarios: [],
        listaProdutosHeader: [
        	{sortable: false, key: "id", label:"Id"},
			{sortable: false, key: "nome", label:"Nome"},
			{sortable: false, key: "setor.id", label:"IdSetor"},
			{sortable: false, key: "setor.nome", label:"Setor"},
			{sortable: false, key: "salario", label:"Salário"},
			{sortable: false, key: "email", label:"Email"},
			{sortable: false, key: "idade", label:"Idade"}
		],
		editar:false,
		exibeListar:true,
		func:{
			id:"",
			nome:"",
			setor:{
				id:"1",
				nome:""
			},
			salario:"",
			email:"",
			idade:""
		},
    },
    created: function(){
        let vm =  this;
        vm.listaFuncionarios();
    },
    methods:{
        listaFuncionarios: function(){
			const vm = this;
			const formCadastro = cadastro;
			const btCad = btCadastro;
			axios.get("/funcionarios")
			.then(response => {
				vm.funcionarios = response.data;
				vm.exibeListar = true;
				formCadastro.exibe = false;
				btCad.exibe = true;
			}).catch(function (error) {
				//vm.mostraAlertaErro("Erro interno", "Não foi possível listar funcionários");
			}).finally(function() {
			});
		},
		removerFuncionario(id){
			const vm = inicio;
			axios.delete("/funcionarios/"+id).then(response =>{
				vm.listaFuncionarios();
			});
		},
		exibeEdicao(funcionario){
			const vm = this;
			const formCadastro = cadastro;
			const btCad = btCadastro;
			if(vm.editar == false){
				vm.editar = true;
				vm.exibeListar = false;
				formCadastro.exibe = false;
				btCad.exibe = false;
				vm.func.id = funcionario.id;
				vm.func.nome = funcionario.nome;
				vm.func.setor.id = funcionario.setor.id;
				vm.func.setor.nome = funcionario.setor.nome;
				vm.func.salario = funcionario.salario;
				vm.func.email = funcionario.email;
				vm.func.idade = funcionario.idade;
			}
			else{
				vm.editar = false;
				formCadastro.exibe = false;
				vm.exibeListar = true;
				btCad.exibe = false;
			}
		},
		editaFuncionario(){
			const formCadastro = cadastro;
			axios.put("/funcionarios/"+this.func.id,this.func).then(response =>{
				this.editar = false;
				formCadastro.exibe = true;
				this.listaFuncionarios();
			});
		},
		mostraAlertaErro(msg){
		
		}
    }
});
var cadastro = new Vue({
	el:"#form-newFuncionario",
	data:{
		form:{
			nome:"",
			setor:{
				id:"1",
				nome:""
			},
			salario:"",
			email:"",
			idade:""
		},
		exibe:false
	},
	methods:{
		addFuncionario(){
			const vm = inicio;
			axios.post("/funcionarios",this.form).then(response => {
				vm.listaFuncionarios(); 
			});
		},
    }
});

var btCadastro = new Vue({
	el:"#novoRegistro",
	data:{
		exibe:true
	},
	methods:{
		exibeFormCadastro(){
			const lista = inicio;
			const formCadastro = cadastro;
			this.exibe = false;
			lista.exibeListar = false;
			formCadastro.exibe = true;
		},
	}
});