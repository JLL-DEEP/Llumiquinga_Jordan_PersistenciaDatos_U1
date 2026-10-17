package com.mycompany.protyectopersistencia001;

import com.mycompany.protyectopersistencia001.entities.Ruta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class ProtyectoPersistencia001 {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("ArcaContinentalPU")) {
            try (EntityManager manager = emf.createEntityManager()) {

                System.out.println("=== INICIANDO CRUD DE RUTAS ARCA CONTINENTAL ===");

                //  C (Crear una ruta nueva)
                manager.getTransaction().begin();
                Ruta nuevaRuta = new Ruta();
                nuevaRuta.setOrigen("Cuenca");
                nuevaRuta.setDestino("Manta");
                nuevaRuta.setDistancia(380.5);
                manager.persist(nuevaRuta);
                manager.getTransaction().commit();
                System.out.println("\n[CREATE] Nueva ruta a Manta guardada exitosamente.");
                //  R (Leer todas las rutas de la base de datos)
                List<Ruta> rutas = manager.createQuery("SELECT r FROM Ruta r", Ruta.class).getResultList();
                System.out.println("\n[READ] --- Lista de rutas actuales ---");
                int idNuevaRuta = 0; 
                for (Ruta r : rutas) {
                    System.out.println("ID: " + r.getId() + " | " + r.getOrigen() + " -> " + r.getDestino() + " | " + r.getDistancia() + " km");
                    idNuevaRuta = r.getId(); // Guardamos el ID de la última ruta insertada
                }
                //  U (Actualizar la ruta que acabamos de crear)
                manager.getTransaction().begin();
                Ruta rutaAActualizar = manager.find(Ruta.class, idNuevaRuta); 
                if (rutaAActualizar != null) {
                    rutaAActualizar.setDestino("Machala"); 
                    rutaAActualizar.setDistancia(200.0);
                    manager.merge(rutaAActualizar);
                    System.out.println("\n[UPDATE] Ruta ID " + idNuevaRuta + " actualizada. Nuevo destino: " + rutaAActualizar.getDestino());
                }
                manager.getTransaction().commit();
                //  D (Eliminar la ruta que acabamos de actualizar)
                manager.getTransaction().begin();
                Ruta rutaAEliminar = manager.find(Ruta.class, idNuevaRuta);
                if (rutaAEliminar != null) {
                    manager.remove(rutaAEliminar);
                    System.out.println("\n[DELETE] Ruta ID " + idNuevaRuta + " eliminada de la base de datos.");
                }
                manager.getTransaction().commit();

                System.out.println("\n=== CRUD FINALIZADO CON EXITO ===");
            }
        } catch (Exception e) {
            System.err.println("Error al ejecutar el CRUD: " + e.getMessage());
            e.printStackTrace();
        }
    }
}