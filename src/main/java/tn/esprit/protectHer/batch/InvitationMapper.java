package tn.esprit.protectHer.batch;

import org.springframework.jdbc.core.RowMapper;

import tn.esprit.protectHer.entity.GenderType;
import tn.esprit.protectHer.entity.Invitation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InvitationMapper implements RowMapper<Invitation> {
	
    @Override
    public Invitation mapRow(ResultSet rs, int rowNum) throws SQLException {
    	return Invitation
                .builder()
                .invitationId(rs.getLong("invitation_id"))
                .email(rs.getString("email"))
                .firstName(rs.getString("first_name"))
                .gender(GenderType.valueOf(rs.getString("gender")))
                .lastName(rs.getString("last_name"))
                .status(rs.getBoolean("status"))
                .build();
    }
    
}
