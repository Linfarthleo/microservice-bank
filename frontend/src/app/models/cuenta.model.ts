export interface Cliente {
    id: number;
    nombre: string;
}

export interface Cuenta {
    id?: number;
    numeroCuenta: string;
    tipoCuenta: string;
    saldoInicial: number;
    estado: boolean;
    clienteId?: number;
    cliente?: Cliente;
}


