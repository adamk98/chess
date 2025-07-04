package chess;

import java.util.Collection;
import java.util.ArrayList;


/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private final ChessGame.TeamColor pieceColor;
    private final PieceType type;



    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {

        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {

        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */

    //this method pieceMoves returns 'theMoves collection' from RookMoveCalculator
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        if(this.type == PieceType.ROOK){
            RookMoveCalculator rookCalc = new RookMoveCalculator(pieceColor);
            return rookCalc.pieceMoves(board, myPosition);
        }
        if(this.type == PieceType.BISHOP){
            BishopMovesCalculator bishopCalc = new BishopMovesCalculator(pieceColor);
            return bishopCalc.pieceMoves(board, myPosition);
        }


    return null;
    }
}
