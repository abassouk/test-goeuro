package gr.bassoukos.goeuro;

import gr.bassoukos.goeuro.om.Position;

import java.util.List;

import javax.ws.rs.core.GenericType;

/**
 * Class that represents a type token for Jersey; work-around needed due to
 * erasure of type parameters.
 * 
 * @author abas
 */
public class ListOfPositions extends GenericType<List<Position>> {
}